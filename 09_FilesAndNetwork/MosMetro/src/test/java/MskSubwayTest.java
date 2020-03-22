import SBPMetro.Main;
import org.junit.jupiter.api.Test;
import java.io.IOException;

class MskSubwayTest {
    @Test
    void parse() throws IOException {
        MskSubway s = new MskSubway();
        s.parse("src/main/java/resources/MetroMsk.html");
        s.toJson(s, "resources/Moscow.json");
    
        Main metro=new Main("resources/Moscow.json");
        metro.main(new String[0]);
    }
}