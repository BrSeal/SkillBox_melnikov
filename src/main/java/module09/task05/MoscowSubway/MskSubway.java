package module09.task05.MoscowSubway;

import module08.task02.SBPMetro.core.Line;
import module08.task02.SBPMetro.core.Station;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.TreeMap;

public class MskSubway
{
	
	
	private TreeMap<Integer, Line> lines;
	private TreeMap<Integer, ArrayList<Station>> stationsOrdered;
	private HashSet<TreeMap<Integer, String>> connections;
	
	public TreeMap<Integer, Line> getLines() {
		return lines;
	}
	
	public TreeMap<Integer, ArrayList<Station>> getStationsOrdered() {
		return stationsOrdered;
	}
	
	public HashSet<TreeMap<Integer, String>> getConnections() {
		return connections;
	}
	
	public MskSubway() {
		lines = new TreeMap<>();
		stationsOrdered = new TreeMap<>();
		connections = new HashSet<>();
	}
	
	public void parse(String url) throws IOException {
		Document doc;
        if (url.startsWith("http")) { doc = Jsoup.connect(url).get(); }
        else {
            File html = new File(url);
            if (!html.exists()) { throw new FileNotFoundException(); }
            doc = Jsoup.parse(html, "UTF-8");
        }
		
		Elements rows = doc.select("table[class='standard sortable jquery-tablesorter']").select("tbody").select("tr");
		
		for (Element row : rows) {
			Elements cols = row.select("td");
			getData(cols);
		}
	}
	
	private void getData(Elements cols) {
		String lineName = cols.get(0).select("span").get(1).attr("title");
		String stationName = cols.get(1).select("a").get(0).text();
		int num = getNum(cols);
		
		//line
		Line line = new Line(num, lineName);
		lines.put(num, line);
		
		//station
		Station station = new Station(stationName, line);
		addToStationsMap(num, station);
		
		//connection
		Elements connected = cols.get(3).select("span");
		if (connected.size()/2 > 0) {
			TreeMap<Integer, String> localConn = new TreeMap<>();
			localConn.put(num, stationName);
			
			for (int i=0;i<connected.size() ; i+=2) {
			    int numLineConnected=Integer.parseInt(connected.get(i).text().replace('А','1'));
                String nameConnected=purifyName(connected.get(i+1).attr("title"));
				localConn.put(numLineConnected, nameConnected);
			}
			connections.add(localConn);
		}
	}
	
	private int getNum(Elements cols) {
		return Integer.parseInt(cols.get(0).select("span").first().text().replace("А", "1"));
	}
	
	private void addToStationsMap(int lineNum, Station station) {
		if (!stationsOrdered.containsKey(lineNum)) {
			stationsOrdered.put(lineNum, new ArrayList<>());
		}
		stationsOrdered.get(lineNum).add(station);
	}
	
	private String purifyName(String name) {
		int trimPlace = name.contains("Кросс") ? 41 : 19;
		return name.substring(trimPlace).split(" \\(?[А-Я]?\\S+-? [а-я]+ ?[а-я]+\\)?")[0];
	}
}


