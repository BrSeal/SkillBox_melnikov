package module08.task01.ConsoleCustomerList;

import module05.task03.EMailList.WrongMailException;
import module08.task01.ConsoleCustomerList.Exceptions.NoCustomerException;
import module08.task01.ConsoleCustomerList.Exceptions.NotEnoughDataException;
import module08.task01.ConsoleCustomerList.Exceptions.PhoneNumberFormatException;
import module08.task01.ConsoleCustomerList.Exceptions.WrongCommandException;

import java.util.Scanner;

public class Main
{
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
	private static String defaultError = "Something gone wrong!";
	
	
	
	public static void main(String[] args)
	{
		Scanner scanner = new Scanner(System.in);
		CustomerStorage executor = new CustomerStorage();
		for (; ; )
		{
			String command = scanner.nextLine();
			String[] tokens = command.split("\\s+", 2);
			try
			{
				switch (tokens[0].toLowerCase())
				{
					case "add":
						executor.addCustomer(tokens[1]);
						break;
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
                    case "remove":
						executor.removeCustomer(tokens[1]);
						break;
					default:
						throw new WrongCommandException();
				}
			}catch (NotEnoughDataException e)
			{
				System.out.println(dataError);
			}catch (PhoneNumberFormatException e)
			{
				System.out.println(phoneError);
			}catch (WrongMailException e)
			{
				System.out.println(mailError);
			}catch (WrongCommandException e)
			{
				System.out.println(commandError);
			}catch (NoCustomerException e)
            {
                System.out.println(removeError);
            }catch (Exception e)
			{
				System.out.println(defaultError);
				e.printStackTrace();
			}
		}
	}
}
