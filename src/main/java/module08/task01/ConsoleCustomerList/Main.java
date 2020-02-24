package module08.task01.ConsoleCustomerList;

import module05.task03.EMailList.WrongMailException;
import module08.task01.ConsoleCustomerList.Exceptions.*;

import java.util.Scanner;

public class Main {
    //help messages
    private static String addCommand = "add Василий Петров vasily.petrov@gmail.com +79215637722";
    private static String commandExamples = "\t" + addCommand + "\n\tlist\n\tcount\n\tremove Василий Петров";
    private static String helpText = "Command examples:\n" + commandExamples;
    private static String exitMessage = "Goodbye!";

    //Errors
    private static String commandError = "Wrong command! Available command examples: \n" + commandExamples;
    private static String dataError = "Not enough data! You must print Name, surname, phone number and eMail.";
    private static String phoneError = "Wrong phone number format!";
    private static String mailError = "Wrong eMail format!";
    private static String removeError = "No such customer in list!";
    private static String nameError = "Name contains illegal characters!";
    private static String emptyListError = "List of customers is empty!";
    private static String defaultError = "Something gone wrong!";


    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        CustomerStorage executor = new CustomerStorage();
        for (; ; ) {
            String command = scanner.nextLine();
            String[] tokens = command.split("\\s+", 2);
            try {
                if (tokens.length == 1) {
                    if (tokens[0].toLowerCase().equals("count")) {
                        System.out.println("There are " + executor.getCount() + " customers");
                    } else if (tokens[0].toLowerCase().equals("exit")) {
                        System.out.println(exitMessage);
                        System.exit(0);
                    } else if (tokens[0].toLowerCase().equals("help")) {
                        System.out.println(helpText);
                    } else if (tokens[0].toLowerCase().equals("list")) {
                        executor.listCustomers();
                    } else {
                        throw new WrongCommandException();
                    }
                } else if (tokens[0].toLowerCase().equals("add")) {
                    executor.addCustomer(tokens[1]);
                } else if (tokens[0].toLowerCase().equals("remove")) {
                    executor.removeCustomer(tokens[1]);
                } else {
                    throw new WrongCommandException();
                }

            } catch (NotEnoughDataException e) {
                System.out.println(dataError);
            } catch (PhoneNumberFormatException e) {
                System.out.println(phoneError);
            } catch (WrongMailException e) {
                System.out.println(mailError);
            } catch (WrongCommandException e) {
                System.out.println(commandError);
            } catch (NoCustomerException e) {
                System.out.println(removeError);
            } catch (WrongNameEception e) {
                System.out.println(nameError);
            } catch (ClientListEmptyException e) {
                System.out.println(emptyListError);
            } catch (Exception e) {
                System.out.println(defaultError);
                e.printStackTrace();
            }
        }
    }
}
