package module08.task01.ConsoleCustomerList;

import module05.task03.EMailList.WrongMailException;
import module08.task01.ConsoleCustomerList.Exceptions.NoCustomerException;
import module08.task01.ConsoleCustomerList.Exceptions.NotEnoughDataException;
import module08.task01.ConsoleCustomerList.Exceptions.PhoneNumberFormatException;

import javax.swing.text.MaskFormatter;
import java.text.ParseException;
import java.util.HashMap;

public class CustomerStorage
{
	private HashMap<String, Customer> storage;
	
	public CustomerStorage()
	{
		storage = new HashMap<>();
	}
	
	public void addCustomer(String data) throws WrongMailException, PhoneNumberFormatException, NotEnoughDataException, ParseException
	{
		String[] components = data.split("\\s+");
		if (components.length < 4)
		{
			throw new NotEnoughDataException();
		}
		boolean isMail = isValidMail(components[2]);
		boolean isPhoneNum = isPhoneNumber(components[3]);
		
		if (!isMail)
		{
			throw new WrongMailException();
		}
		if (!isPhoneNum)
		{
			throw new PhoneNumberFormatException();
		}
		
		
		String name = components[0] + " " + components[1];
		storage.put(name, new Customer(name, formatNumber(components[3]), components[2]));
	}
	
	public void listCustomers()
	{
		storage.values().forEach(System.out::println);
	}
	
	public void removeCustomer(String name) throws NoCustomerException
	{
		if(storage.containsKey(name)) storage.remove(name);
		else throw new NoCustomerException();
	}
	
	public int getCount()
	{
		return storage.size();
	}
	
	private boolean isValidMail(String mail)
	{
		
		// RFC 5322 Official Standard
		
		return mail.matches("(?:[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*|\"(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21\\x23-\\x5b\\x5d-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])*\")@(?:(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?|\\[(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?|[a-z0-9-]*[a-z0-9]:(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21-\\x5a\\x53-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])+)\\])");
	}
	
	private boolean isPhoneNumber(String input)
	{
		return input.replaceAll("[()\\- +\\[\\]]", "").matches("\\d{11}");
	}
	
	private String formatNumber(String phoneNumber) throws ParseException
	{
		StringBuilder rawPhoneNum = new StringBuilder(phoneNumber.replaceAll("[()\\- +\\[\\]]", ""));
		
		MaskFormatter mf = new MaskFormatter();
		mf.setValueContainsLiteralCharacters(false);
		mf.setMask("+#(###)###-##-##");
		
		if (rawPhoneNum.indexOf("8") == 0)
		{
			rawPhoneNum.replace(0, 1, "7");
		}
		return mf.valueToString(rawPhoneNum);
	}
}