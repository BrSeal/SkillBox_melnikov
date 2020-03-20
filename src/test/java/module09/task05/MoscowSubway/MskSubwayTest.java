package module09.task05.MoscowSubway;

import org.junit.jupiter.api.Test;
import java.io.IOException;

class MskSubwayTest {
    @Test
    void parse() throws IOException {
        MskSubway s = new MskSubway();
        s.parse("src/main/resources/09_FilesAndNetwork/files/MetroMsk.html");
        s.toJson(s, "src/main/resources/Moscow.json");
    }
}