package module05.task03.EMailList;

import java.util.HashSet;
import java.util.Scanner;

public class Loader
{
    private static final String EXIT = "EXIT";
    private static final String LIST = "LIST";
    private static final String ADD = "ADD";
    private static final String DELETE = "DELETE";

    private static HashSet<String> mails;
    private Scanner scanner;


    public static void main(String[] args)
    {
        mails = new HashSet<>();
        Loader l = new Loader();
        l.scanner = new Scanner(System.in);
        System.out.println("Введите команду:");
        String input;
        while (true)
        {

            input = l.scanner.nextLine();
            l.command(input);
        }

    }

    private void command(String input)
    {
        String[] in=input.split(" ",2);
        try
        {
            switch (in[0].toUpperCase())
            {
                case EXIT:
                    exit();

                case LIST:
                    printMap();
                    break;

                case ADD:
                    addToMap(in[1]);
                    break;

                case DELETE:
                    deleteFromMap(in[1]);
                    break;

                default:
                    System.out.println("Неверно набрана команда!");
            }
        }catch (WrongMailException e)
        {
            System.out.println("Неверный формат е-мейла!");
        }
    }

    private void exit() {
        scanner.close();
        System.exit(0);
    }

    private void printMap() {
        if (mails.isEmpty()) {
            System.out.println("Список электронных адресов пуст");
        } else {
            int i=0;
            for (String task: mails) {
                System.out.println(i + " " + task);
                i++;
            }
        }
    }

    private void addToMap(String in) throws WrongMailException{
        if (!isValidMail(in)) throw new WrongMailException();
        mails.add(in);
    }

    private void deleteFromMap(String in) {
            mails.remove(in);
    }

    private boolean isValidMail(String mail) {

        // RFC 5322 Official Standard

        return mail.matches("(?:[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*|\"(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21\\x23-\\x5b\\x5d-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])*\")@(?:(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?|\\[(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?|[a-z0-9-]*[a-z0-9]:(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21-\\x5a\\x53-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])+)\\])");
    }
}