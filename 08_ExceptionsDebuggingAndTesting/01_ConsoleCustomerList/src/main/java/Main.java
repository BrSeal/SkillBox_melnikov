import Exceptions.*;

import java.util.Scanner;

public class Main
{
	//help messages
	private static String addCommand = "add Василий Петров vasily.petrov@gmail.com +79215637722";
	private static final String commandExamples = "\t" + addCommand + "\n\tlist\n\tcount\n\tremove Василий Петров";
	private static final String helpText = "Command examples:\n" + commandExamples;
	private static final String exitMessage = "Goodbye!";
	
	//Errors
	private static final String commandError = "Wrong command! Available command examples: \n" + commandExamples;
	private static final String dataError = "Not enough data! You must print Name, surname, phone number and eMail.";
	private static final String phoneError = "Wrong phone number format!";
	private static final String mailError = "Wrong eMail format!";
	private static final String removeError = "No such customer in list!";
	private static final String nameError = "Name contains illegal characters!";
	private static final String emptyListError = "List of customers is empty!";
	private static final String defaultError = "Something gone wrong!";
	
	
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		CustomerStorage executor = new CustomerStorage();
		for (; ; ) {
			String command = scanner.nextLine();
			String[] tokens = command.split("\\s+", 2);
			try {
				if (tokens.length == 1) {
                    switch (tokens[0].toLowerCase()) {
                        case "count":
                            System.out.println("There are " + executor.getCount() + " customers");
                            break;
                        case "exit":
                            System.out.println(exitMessage);
                            System.exit(0);
                        case "help":
                            System.out.println(helpText);
                            break;
                        case "list":
                            executor.listCustomers();
                            break;
                        default:
                            throw new WrongCommandException();
                    }
				}
				else if (tokens[0].toLowerCase().equals("add")) {
					executor.addCustomer(tokens[1]);
				}
				else if (tokens[0].toLowerCase().equals("remove")) {
					executor.removeCustomer(tokens[1]);
				}
				else {
					throw new WrongCommandException();
				}
				
			}catch (NotEnoughDataException e) {
				System.out.println(dataError);
			}catch (PhoneNumberFormatException e) {
				System.out.println(phoneError);
			}catch (WrongMailException e) {
				System.out.println(mailError);
			}catch (WrongCommandException e) {
				System.out.println(commandError);
			}catch (NoCustomerException e) {
				System.out.println(removeError);
			}catch (WrongNameEception e) {
				System.out.println(nameError);
			}catch (ClientListEmptyException e) {
				System.out.println(emptyListError);
			}catch (Exception e) {
				System.out.println(defaultError);
				e.printStackTrace();
			}
		}
	}
}
