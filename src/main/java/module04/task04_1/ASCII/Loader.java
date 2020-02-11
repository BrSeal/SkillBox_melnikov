package module04.task04_1.ASCII;

public class Loader {

    public static void main(String[] args) {
        for (int i = 65; i < 90; i++) {
            System.out.println("Symbol: " + (char) (i + 32) + " code: " + (i + 32));
            System.out.println("Symbol: " + (char) i + " code: " + i);
        }

    }
}
