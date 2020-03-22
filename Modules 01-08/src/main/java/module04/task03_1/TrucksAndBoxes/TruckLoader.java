package module04.task03_1.TrucksAndBoxes;

import java.util.Scanner;

public class TruckLoader {

    private static final int CONTAINERS_INTRUCK = 12;
    private static final int BOXEX_IN_CONTAINER = 27;

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

            if (box % (CONTAINERS_INTRUCK * BOXEX_IN_CONTAINER) == 0) {
                truck++;
                System.out.println("Грузовик " + truck + ":");
            }

            if (box % BOXEX_IN_CONTAINER == 0) {
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
