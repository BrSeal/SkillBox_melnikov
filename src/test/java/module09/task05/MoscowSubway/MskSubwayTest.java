package module09.task05.MoscowSubway;

import module08.task02.SBPMetro.core.Line;
import module08.task02.SBPMetro.core.Station;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;

class MskSubwayTest {
    @Test
    void parse() throws IOException {
        MskSubway s = new MskSubway();
        s.parse("src/main/resources/09_FilesAndNetwork/files/MetroMsk.html");
        for (Map.Entry<Integer, Line> e : s.getLines().entrySet())
            System.out.println(e.getKey() + " " + e.getValue().getName());
        System.out.println();
        for (Map.Entry<Integer, ArrayList<Station>> e : s.getStationsOrdered().entrySet())
            System.out.println(e.getKey() + " " + e.getValue());
        System.out.println();
        s.getConnections().forEach(System.out::println);

        MskSubway r = new MskSubway();
        r.parse("https://ru.wikipedia.org/wiki/%D0%A1%D0%BF%D0%B8%D1%81%D0%BE%D0%BA_%D1%81%D1%82%D0%B0%D0%BD%D1%86%D0%B8%D0%B9_%D0%9C%D0%BE%D1%81%D0%BA%D0%BE%D0%B2%D1%81%D0%BA%D0%BE%D0%B3%D0%BE_%D0%BC%D0%B5%D1%82%D1%80%D0%BE%D0%BF%D0%BE%D0%BB%D0%B8%D1%82%D0%B5%D0%BD%D0%B0");
        for (Map.Entry<Integer, Line> e : r.getLines().entrySet())
            System.out.println(e.getKey() + " " + e.getValue().getName());
        System.out.println();
        for (Map.Entry<Integer, ArrayList<Station>> e : r.getStationsOrdered().entrySet())
            System.out.println(e.getKey() + " " + e.getValue());
        System.out.println();
        r.getConnections().forEach(System.out::println);
    }
}