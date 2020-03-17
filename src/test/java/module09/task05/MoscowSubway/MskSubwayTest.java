package module09.task05.MoscowSubway;

import module08.task02.SBPMetro.core.Line;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.Map;

class MskSubwayTest {

    @Test
    void parse() throws IOException {
        MskSubway s = new MskSubway();
        s.parse("src/main/resources/09_FilesAndNetwork/files/MetroMsk.html");
        for (Map.Entry<Integer, Line> e : s.getLines().entrySet()) {
            System.out.println(e.getKey() + " " + e.getValue().getName());
        }
    }
}