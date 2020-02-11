package module05.task04.Phone_Book;

import javax.swing.text.MaskFormatter;
import java.text.ParseException;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;

public class Loader {
    private static final String EXIT = "EXIT";
    private static final String LIST = "LIST";
    private static final String HELP = "HELP";

    private static TreeMap<String, String> phones;
    private Scanner scanner;

    private Loader() {
        phones = new TreeMap<>();
        scanner = new Scanner(System.in);
    }

    public static void main(String[] args) {
        Loader loader = new Loader();
        while (true) {
            System.out.println("Введите команду, номер или имя контакта:");
            loader.command(loader.scanner.nextLine());
        }

    }

    private void command(String input) {
        switch (input.toUpperCase()) {
            case EXIT:
                exit();

            case HELP:
                help();
                break;

            case LIST:
                printMap();
                break;

            default:
                defaultAction(input);
        }
    }

    private void exit() {
        scanner.close();
        System.exit(0);
    }

    private void help() {
        System.out.println("Допустимые команды:\nLIST \nHELP\nEXIT\n");
        System.out.println("Также можно ввести имя или номер интересующего контакта\n" +
                "Если такового не найдется, то система предложит ввести \n" +
                "недостающие данные и внесет новый контакт в телефонную \n" +
                "книгу. Номер должен состоять из 11 цифр и может содержать \n" +
                "знаки (, ), -, +,[, ]");
    }

    private void printMap() {
        if (phones.isEmpty()) {
            System.out.println("Телефонная книга пуста");
        } else {
            for (String key : phones.keySet()) {
                printContact(key);
            }
        }
    }

    private void defaultAction(String input) {
        if (input.isEmpty()) {
            System.out.println("Вы не ввели команду!");
        } else {
            String key = getKey(input);

            if (key == null) {
                addToMap(input);
            } else {
                printContact(key);
            }
        }
    }

    private void printContact(String key) {
        try {
            System.out.printf("Имя: %s\nНомер: %s\n", key, formatNumber(phones.get(key)));
            System.out.println("=========================");
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    private String formatNumber(String phoneNumber) throws ParseException {
        StringBuilder rawPhoneNum = new StringBuilder(phoneNumber);
        MaskFormatter mf = new MaskFormatter();
        mf.setValueContainsLiteralCharacters(false);
        mf.setMask("+#(###)###-##-##");


        if (rawPhoneNum.indexOf("8") == 0) {
            rawPhoneNum.replace(0, 1, "7");
        }

        return mf.valueToString(rawPhoneNum);
    }

    private void addToMap(String input) {
        String number = makeNumOrNull(input);
        String name = null;

        while (number == null || name == null) {

            if (number == null) {
                name = input;
                System.out.println("Введите номер контакта:");
                if ((number = makeNumOrNull(scanner.nextLine())) == null) {
                    System.out.println("Номер должен состоять из 11 цифр и может содержать знаки (, ), -, +,[, ]");
                }
            } else {
                System.out.println("Введите имя контакта:");
                name = scanner.nextLine();
            }
        }
        phones.put(name, number);
    }

    private String makeNumOrNull(String input) {
        String num = input.replaceAll("[()\\- +\\[\\]]", "");
        if (num.startsWith("8")) {
            num = 7 + num.substring(1);
        }
        if (num.matches("\\d{11}")) {
            return num;
        }
        return null;

    }

    private String getKey(String input) {
        if (phones.containsKey(input)) {
            return input;
        } else {
            String in = makeNumOrNull(input);
            if (phones.containsValue(in)) {

                for (Map.Entry<String, String> contact : phones.entrySet()) {
                    if (contact.getValue().equals(in)) {
                        return contact.getKey();
                    }
                }
            }
            return null;
        }
    }
}



