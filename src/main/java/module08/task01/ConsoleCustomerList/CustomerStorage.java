package module08.task01.ConsoleCustomerList;

import module05.task03.EMailList.WrongMailException;
import module08.task01.ConsoleCustomerList.Exceptions.*;

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
	
	public void addCustomer(String data) throws WrongMailException, PhoneNumberFormatException, NotEnoughDataException, ParseException,WrongNameEception
	{
		String[] components = data.split("\\s+");
		
		if (components.length < 4) throw new NotEnoughDataException();
		
		String name = components[0] + " " + components[1];
		
		if(!name.matches("[а-яА-Я ]+")) throw new WrongNameEception();
		if (!isValidMail(components[2])) throw new WrongMailException();
		if (!isPhoneNumber(components[3])) throw new PhoneNumberFormatException();
		
		storage.put(name, new Customer(name, formatNumber(components[3]), components[2]));
	}
	
	public void listCustomers()
	{
		storage.values().forEach(System.out::println);
	}
	
	public void removeCustomer(String name) throws NoCustomerException, WrongCommandException
	{
		if(name.isEmpty()||name==null) throw new WrongCommandException();
		if(storage.containsKey(name)) storage.remove(name);
		else throw new NoCustomerException();
	}
	
	public int getCount()
	{
		return storage.size();
	}
	
	private boolean isValidMail(String mail)
	{
		return mail.matches("^\\w[.\\w]+@\\w[.\\w]+\\.\\w{2,5}");
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