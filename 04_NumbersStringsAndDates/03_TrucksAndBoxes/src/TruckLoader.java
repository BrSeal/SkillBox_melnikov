import java.util.Scanner;

public class TruckLoader {

    private static final int CONTAINERS_IN_TRUCK = 12;
    private static final int BOXES_IN_CONTAINER = 27;

    public static void main(String[] args) {

        // input

        Scanner s = new Scanner(System.in);
        String input;
        while (!isParsable(input = s.nextLine())) ;
        int boxNum = Integer.parseInt(input);

        // logic

        int truck = 0;
        int container = 0;

        for (int box = 0; box < boxNum; box++) {

            if (box % (CONTAINERS_IN_TRUCK * BOXES_IN_CONTAINER) == 0) {
                truck++;
                System.out.println("Грузовик " + truck + ":");
            }

            if (box % BOXES_IN_CONTAINER == 0) {
                container++;
                System.out.println("\tКонтейнер " + container + ":");
            }

            System.out.println("\t\tКоробка " + (box + 1));
        }

    }

    private static boolean isParsable(String s) {
        try {
            Integer.parseInt(s);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

}
