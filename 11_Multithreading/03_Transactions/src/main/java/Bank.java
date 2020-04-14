import Exceptions.BlockedAccountException;
import Exceptions.NoEnoughMoneyException;
import lombok.Getter;

import java.util.Map;
import java.util.Random;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

public class Bank
{
	private static final AtomicInteger COUNT = new AtomicInteger(0);
	private static final String SAME_ACC_ERR = "You can't transfer money to the same account!";
	private static final String ZERO_OR_LESS_ERR = "Transfer amount can't be 0 or less!";
	private static final String BLOCKED_ERR = " Account is blocked!";
	private static final String NO_MONEY_ERR = " Not enough money!";
	
	@Getter
	private ConcurrentHashMap<String, Account> accounts;
	private final Random random = new Random();
	
	public Bank() {
		this.accounts = new ConcurrentHashMap<>();
	}
	
	public synchronized boolean isFraud(String fromAccountNum, String toAccountNum, long amount) throws InterruptedException {
		Thread.sleep(1000);
		return random.nextBoolean();
	}
	
	/**
	 * TODO: реализовать метод. Метод переводит деньги между счетами.
	 * Если сумма транзакции > 50000, то после совершения транзакции,
	 * она отправляется на проверку Службе Безопасности – вызывается
	 * метод isFraud. Если возвращается true, то делается блокировка
	 * счетов (как – на ваше усмотрение)
	 */
	
	public void transfer(String fromAccountNum, String toAccountNum, long amount) throws InterruptedException, BlockedAccountException, NoEnoughMoneyException {
		
		if (fromAccountNum.equals(toAccountNum)) { throw new IllegalArgumentException(SAME_ACC_ERR); }
		if (amount <= 0) { throw new IllegalArgumentException(ZERO_OR_LESS_ERR); }
		Account from = accounts.get(fromAccountNum);
		Account to = accounts.get(toAccountNum);
		
		synchronized (from.compareTo(to) > 0 ? from : to) {
			synchronized (from.compareTo(to) > 0 ? to : from) {
				if (from.isBlocked()) { throw new BlockedAccountException(from.getAccNumber() + BLOCKED_ERR); }
				if (from.getMoney() < amount) { throw new NoEnoughMoneyException(from.getAccNumber() + NO_MONEY_ERR); }
				
				from.withdraw(amount);
				if (amount > 50000 && isFraud(fromAccountNum, toAccountNum, amount)) {
					from.deposit(amount);
					from.setBlocked(true);
					to.setBlocked(true);
				}
				else {
					if (to.isBlocked()) {
						from.deposit(amount);
						throw new BlockedAccountException(to.getAccNumber() + BLOCKED_ERR);
					}
					else { to.deposit(amount); }
				}
			}
		}
	}
	
	/**
	 * TODO: реализовать метод. Возвращает остаток на счёте.
	 */
	
	public long getBalance(String accountNum) {
		Account acc = accounts.get(accountNum);
		synchronized (acc) {
			return acc.getMoney();
		}
	}
	
	public void addAccount(long amount) {
		int accNum = COUNT.getAndIncrement();
		accounts.put(accNum + "", new Account(accNum + "", amount));
		
	}
	
	public Account getAccount(String key) {
		return accounts.get(key);
	}
	
	public synchronized long getTotalMoneyPool() {
		long res = 0;
		for (Map.Entry<String, Account> a : accounts.entrySet()) res += a.getValue().getMoney();
		return res;
	}
}
