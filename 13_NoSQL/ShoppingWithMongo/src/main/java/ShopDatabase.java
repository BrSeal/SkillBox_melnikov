import java.util.Scanner;

public class ShopDatabase {
    private static final String HELP = "HELP";
    private static final String ADD_SHOP = "SHOP";
    private static final String ADD_ITEM = "ITEM";
    private static final String ITEM_TO_SHOP = "MOVE";
    private static final String REPORT = "REPORT";
    private static final String EXIT = "EXIT";

    private static final MongoRepository repository=new MongoRepository();
    private static Scanner scanner = new Scanner(System.in);
    public static void main(String[] args) {

        while (true) {
            System.out.println("Введите команду:");
            command(scanner.nextLine());
        }
    }

    private static void command(String input) {
        String[] in = input.split(" ");

        switch (in[0].toUpperCase()) {
            case ADD_SHOP:
                repository.addShop(in[1]);
                break;

            case ADD_ITEM:
                repository.addItem(in[1]);
                break;

            case ITEM_TO_SHOP:
                repository.goodToShop(in[1]);
                break;

            case REPORT:
                repository.report();
                break;

            case HELP:
                help();
                break;

            case EXIT:
                exit();

            default:
                error();
        }
    }

    private static void exit(){
        scanner.close();
        System.exit(0);
    }

    private static void help() {
        System.out.println("Допустимые команды:\n"
                + HELP + " просмотреть список команд\n"
                + ADD_SHOP + " добавить магазин\n"
                + ADD_ITEM + " добавить товар\n"
                + ITEM_TO_SHOP + " переместить товар на магазин\n"
                + REPORT + " статистика по товарам\n"
                + EXIT + " выход из программы");
    }

    private static void error(){
        System.out.println("Неверно набрана команда!");
        help();
    }
}
