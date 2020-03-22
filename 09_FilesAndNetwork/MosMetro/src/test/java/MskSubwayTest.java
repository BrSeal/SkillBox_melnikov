import SBPMetro.Main;
import org.junit.jupiter.api.Test;
import java.io.IOException;

class MskSubwayTest {
    @Test
    void parse() throws IOException {
        MskSubway s = new MskSubway();
        s.parse("src/main/resources/MetroMsk.html");
        s.toJson(s, "src/main/resources/Moscow.json");
    
        Main metro=new Main("resources/Moscow.json");
        metro.main(new String[0]);
    }
}