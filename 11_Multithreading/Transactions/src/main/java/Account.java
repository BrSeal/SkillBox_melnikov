import Exceptions.BlockedAccountException;
import Exceptions.NoEnoughMoneyException;
import lombok.Getter;

public class Account implements Comparable<Account>
{
	private static final String ZERO_OR_LESS_ERR = "Transfer amount can't be 0 or less!";
	private static final String BLOCKED_ERR = " Account is blocked!";
	private static final String NO_MONEY_ERR = " Not enough money!";
	
	@Getter
	private final String accNumber;
	
	private boolean isBlocked;
	private long money;
	
	
	//getters&setters
	public synchronized void setBlocked(boolean blocked) {
		isBlocked = blocked;
	}
	
	public synchronized long getMoney() {
		return money;
	}
	
	public synchronized boolean isBlocked() {
		return isBlocked;
	}
	
	
	//constructor
	public Account(String accNumber, long amount) {
		this.accNumber = accNumber;
		isBlocked = false;
		money = amount;
	}
	
	//logic
	public synchronized void withdraw(long amount) throws BlockedAccountException, NoEnoughMoneyException {
		if (amount <= 0) { throw new IllegalArgumentException(ZERO_OR_LESS_ERR); }
		if (isBlocked) { throw new BlockedAccountException(accNumber + BLOCKED_ERR); }
		if (money < amount) { throw new NoEnoughMoneyException(accNumber + NO_MONEY_ERR); }
		money -= amount;
	}
	
	public synchronized void deposit(long amount) throws BlockedAccountException {
		if (amount <= 0) { throw new IllegalArgumentException(ZERO_OR_LESS_ERR); }
		if (isBlocked) { throw new BlockedAccountException(accNumber + BLOCKED_ERR); }
		money += amount;
	}
	
	@Override
	public int compareTo(Account o) {
		return accNumber.compareTo(o.accNumber);
	}
}
