import lombok.Getter;
import lombok.Setter;

public class Account implements Comparable<Account>
{
	@Getter
	private final String accNumber;
	
	@Getter
	@Setter
	private boolean isBlocked;
	
	@Getter
	private long money;
	
	public Account(String accNumber, long amount) {
		this.accNumber = accNumber;
		isBlocked = false;
		money = amount;
	}
	
	public void withdraw(long amount) {
		money -= amount;
	}
	
	public void deposit(long amount) {
		money += amount;
	}
	
	@Override
	public int compareTo(Account o) {
		return accNumber.compareTo(o.accNumber);
	}
}
