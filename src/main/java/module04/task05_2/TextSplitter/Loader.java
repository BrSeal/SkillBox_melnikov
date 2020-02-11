package module04.task05_2.TextSplitter;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;


public class Loader {

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader("src//main//resources//text.txt"));
        String words[];
        while (reader.ready()) {
            words = reader.readLine().split("\\W");
            for (String i : words) {
                if (i.isEmpty()) continue;
                System.out.println(i);
            }
        }


        reader.close();
    }
}
