import Exceptions.*;

import javax.swing.text.MaskFormatter;
import java.text.ParseException;
import java.util.HashMap;

public class CustomerStorage
{
	String nameCheckRegex = "[А-Я][а-я]+";
	
	private HashMap<String, Customer> storage;
	
	public CustomerStorage() {
		storage = new HashMap<>();
	}
	
	public void addCustomer(String data) throws WrongMailException, PhoneNumberFormatException, NotEnoughDataException, ParseException, WrongNameEception {
		String[] components = data.split("\\s+");
        
        if (components.length < 4) { throw new NotEnoughDataException(); }
        
        
        if (!(components[0].matches(nameCheckRegex) && components[1].matches(nameCheckRegex))) {
            throw new WrongNameEception();
        }
        if (!isValidMail(components[2])) { throw new WrongMailException(); }
        if (!isPhoneNumber(components[3])) { throw new PhoneNumberFormatException(); }
		
		String name = components[0] + " " + components[1];
		storage.put(name, new Customer(name, formatNumber(components[3]), components[2]));
	}
	
	public void listCustomers() throws ClientListEmptyException {
        if (storage.isEmpty()) { throw new ClientListEmptyException(); }
		storage.values().forEach(System.out::println);
	}
	
	public void removeCustomer(String name) throws NoCustomerException, WrongCommandException {
        if (name.isEmpty()) { throw new WrongCommandException(); }
        if (storage.containsKey(name)) { storage.remove(name); }
        else { throw new NoCustomerException(); }
	}
	
	public int getCount() {
		return storage.size();
	}
	
	private boolean isValidMail(String mail) {
		return mail.matches(".+@.+\\..+");
	}
	
	private boolean isPhoneNumber(String input) {
		return input.replaceAll("[()\\- +\\[\\]]", "").matches("\\d{11}");
	}
	
	private String formatNumber(String phoneNumber) throws ParseException {
		StringBuilder rawPhoneNum = new StringBuilder(phoneNumber.replaceAll("[()\\- +\\[\\]]", ""));
		
		MaskFormatter mf = new MaskFormatter();
		mf.setValueContainsLiteralCharacters(false);
		mf.setMask("+#(###)###-##-##");
		
		if (rawPhoneNum.indexOf("8") == 0) {
			rawPhoneNum.replace(0, 1, "7");
		}
		return mf.valueToString(rawPhoneNum);
	}
}