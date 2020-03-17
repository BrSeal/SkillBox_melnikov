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
import java.util.HashMap;
import java.util.TreeMap;
import java.util.TreeSet;

public class MskSubway {


    private TreeMap<Integer, Line> lines;
    private TreeMap<Integer, ArrayList<Station>> stations;
    private TreeMap<Station, TreeSet<Station>> connections;

    public TreeMap<Integer, Line> getLines() {
        return lines;
    }

    public TreeMap<Integer, ArrayList<Station>> getStations() {
        return stations;
    }

    public TreeMap<Station, TreeSet<Station>> getConnections() {
        return connections;
    }

    public MskSubway() {
        lines = new TreeMap<>();
        stations = new TreeMap<>();
        connections = new TreeMap<>();
    }

    void parse(String url) throws IOException {
        Document doc;
        if (url.startsWith("http")) doc = Jsoup.connect(url).get();
        else {
            File html = new File(url);
            if (!html.exists()) throw new FileNotFoundException();
            doc = Jsoup.parse(html, "UTF-8");
        }

        Elements rows = doc.select("table[class='standard sortable jquery-tablesorter']")
                .select("tbody")
                .select("tr");

        for (Element row : rows) {
            Elements cols = row.select("td");
            getLineAndStation(cols);
            getConnection(cols);
        }
    }

    private void getLineAndStation(Elements cols) {
        String lineName = cols.get(0).select("span").get(1).attr("title");
        String stationName = cols.get(1).select("a").get(0).text();
        int num = getNum(cols);
        Line line = new Line(num, lineName);
        addToStationsMap(num, new Station(stationName, line));
        lines.put(num, line);
    }

    private void getConnection(Elements cols) {

    }

    private int getNum(Elements cols) {
        return Integer.parseInt(cols.get(0).select("span").first().text().replace("А", "1"));
    }

    private void addToStationsMap(int lineNum, Station station) {
        if (!stations.containsKey(lineNum)) {
            stations.put(lineNum, new ArrayList<>());
        }
        stations.get(lineNum).add(station);
    }
}


