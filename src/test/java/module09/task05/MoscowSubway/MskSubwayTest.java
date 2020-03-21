package module09.task05.MoscowSubway;

import module08.task02.SBPMetro.Main;
import org.junit.jupiter.api.Test;
import java.io.IOException;

class MskSubwayTest {
    @Test
    void parse() throws IOException {
        MskSubway s = new MskSubway();
        s.parse("src/main/resources/09_FilesAndNetwork/files/MetroMsk.html");
        s.toJson(s, "src/main/resources/Moscow.json");
    
        Main metro=new Main("src/main/resources/Moscow.json");
        metro.main(new String[0]);
    }
}