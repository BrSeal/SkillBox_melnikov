import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import JsonPresenters.ConnectionJsonPresenter;
import JsonPresenters.LineJsonPresenter;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.TreeMap;
import java.util.TreeSet;

public class MskSubway
{
	private TreeSet<LineJsonPresenter> lines;
	private TreeMap<Integer, ArrayList<String>> stations;
	private HashSet<ArrayList<ConnectionJsonPresenter>> connections;
	
	public TreeSet<LineJsonPresenter> getLines() {
		return lines;
	}
	
	public TreeMap<Integer, ArrayList<String>> getStations() {
		return stations;
	}
	
	public HashSet<ArrayList<ConnectionJsonPresenter>> getConnections() {
		return connections;
	}
	
	public MskSubway() {
		lines = new TreeSet<>();
		stations = new TreeMap<>();
		connections = new HashSet<>();
	}
	
	public void parse(String url) throws IOException {
		Document doc;
		if (url.startsWith("http")) {
			doc = Jsoup.connect(url).get();
		}
		else {
			File html = new File(url);
			if (!html.exists()) {
				throw new FileNotFoundException();
			}
			doc = Jsoup.parse(html, "UTF-8");
		}
		
		doc.select("table[class='standard sortable']").select("tbody").select("tr").forEach(row -> getData(row.select("td")));
	}
	
	private void getData(Elements cols) {
		if (cols.size() == 0) { return; }
		ArrayList<Integer> nums = getNum(cols.get(0).select("span"));
		String lineName = cols.get(0).select("span").get(1).attr("title");
		String stationName = cols.get(1).select("a").get(0).text();
		String lineColor = cols.get(0).attr("style");
		lineColor = lineColor.substring(lineColor.indexOf('#') + 1);
		
		Elements connected = cols.get(3).select("span");
		ArrayList<ConnectionJsonPresenter> localConn = new ArrayList<>();
		
		for (int number : nums) {
			//line
			lines.add(new LineJsonPresenter(number, lineName, lineColor));
			
			//station
			addToStationsMap(number, stationName);
			localConn.add(new ConnectionJsonPresenter(number, stationName));
			
		}
		//connection
		if (connected.size() / 2 > 0) {
			for (int i = 0; i < connected.size(); i += 2) {
                if (connected.get(i).text().isEmpty()) { continue; }
				int numLineConnected = getNum(connected.get(i));
				String nameConnected = purifyName(connected.get(i + 1).attr("title"));
				localConn.add(new ConnectionJsonPresenter(numLineConnected, nameConnected));
			}
			connections.add(localConn);
		}
	}
	
	private int getNum(Element element) {
		return Integer.parseInt(element.text().replace("А", "1"));
	}
	
	private ArrayList<Integer> getNum(Elements e) {
		ArrayList<Integer> result = new ArrayList<>();
		for (int i = 1; i < e.size(); i += 2) {
			result.add(Integer.parseInt(e.get(i - 1).text().replace("А", "1")));
		}
		return result;
	}
	
	private void addToStationsMap(int lineNum, String station) {
		if (!stations.containsKey(lineNum)) {
			stations.put(lineNum, new ArrayList<>());
		}
		stations.get(lineNum).add(station);
	}
	
	private String purifyName(String name) {
		int trimPlace = name.contains("Кросс") ? 41 : 19;
		name = name.substring(trimPlace);
		return name.split(" \\(?[А-Я]?\\S+-? [а-я]+ ?[а-я]+\\)?")[0];
	}
	
	public void toJson(Object o, String path) throws IOException {
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		File output=new File(path);
		FileWriter fw = new FileWriter(output);
		gson.toJson(o, fw);
		fw.flush();
		fw.close();
	}
}