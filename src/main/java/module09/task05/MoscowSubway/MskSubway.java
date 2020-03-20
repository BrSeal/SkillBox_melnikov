package module09.task05.MoscowSubway;

import com.google.gson.*;
import module09.task05.MoscowSubway.JsonPresenters.LineAndConnectionJsonPresenter;
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

public class MskSubway {
    private TreeSet<LineAndConnectionJsonPresenter> lines;
    private TreeMap<Integer, ArrayList<String>> stations;
    private HashSet<ArrayList<LineAndConnectionJsonPresenter>> connections;

    public TreeSet<LineAndConnectionJsonPresenter> getLines() {
        return lines;
    }

    public TreeMap<Integer, ArrayList<String>> getStations() {
        return stations;
    }

    public HashSet<ArrayList<LineAndConnectionJsonPresenter>> getConnections() {
        return connections;
    }

    public MskSubway() {
        lines = new TreeSet<>();
        stations = new TreeMap<>();
        connections = new HashSet<ArrayList<LineAndConnectionJsonPresenter>>();
    }

    public void parse(String url) throws IOException {
        Document doc;
        if (url.startsWith("http")) {
            doc = Jsoup.connect(url).get();
        } else {
            File html = new File(url);
            if (!html.exists()) {
                throw new FileNotFoundException();
            }
            doc = Jsoup.parse(html, "UTF-8");
        }

        doc.select("table[class='standard sortable']")
                .select("tbody")
                .select("tr")
                .forEach(row -> getData(row.select("td")));
        }

    private void getData(Elements cols) {
        if(cols.size()==0) return;
        int num = getNum(cols.get(0).select("span").get(0));
        String lineName = cols.get(0).select("span").get(1).attr("title");
        String stationName = cols.get(1).select("a").get(0).text();
        //line
        lines.add(new LineAndConnectionJsonPresenter(num, lineName));

        //station
        addToStationsMap(num, stationName);

        //connection
        Elements connected = cols.get(3).select("span");
        if (connected.size() / 2 > 0) {
            ArrayList<LineAndConnectionJsonPresenter> localConn = new ArrayList<>();
            localConn.add(new LineAndConnectionJsonPresenter(num,stationName));

            for (int i = 0; i < connected.size(); i += 2) {
                int numLineConnected = getNum(connected.get(i));
                String nameConnected = purifyName(connected.get(i + 1).attr("title"));
                localConn.add(new LineAndConnectionJsonPresenter(numLineConnected,nameConnected));
            }
            connections.add(localConn);
        }
    }

    private int getNum(Element element) {
        return Integer.parseInt(element.text()
                .replace("А", "1"));
    }

    private void addToStationsMap(int lineNum, String station) {
        if (!stations.containsKey(lineNum)) {
            stations.put(lineNum, new ArrayList<>());
        }
        stations.get(lineNum).add(station);
    }

    private String purifyName(String name) {
        int trimPlace = name.contains("Кросс") ? 41 : 19;
        return name.substring(trimPlace).split(" \\(?[А-Я]?\\S+-? [а-я]+ ?[а-я]+\\)?")[0];
    }

    public void toJson(Object o,String path) throws IOException{
        Gson gson=new GsonBuilder().setPrettyPrinting().create();
        FileWriter fw=new FileWriter(path);
        gson.toJson(o, fw);
        fw.flush();
        fw.close();
    }
}


