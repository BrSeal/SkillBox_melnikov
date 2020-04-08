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
	
	public synchronized void transfer(String fromAccountNum, String toAccountNum, long amount) throws InterruptedException, BlockedAccountException, NoEnoughMoneyException, IllegalArgumentException {
		if (fromAccountNum.equals(toAccountNum)) { throw new IllegalArgumentException(SAME_ACC_ERR); }
		Account from = accounts.get(fromAccountNum);
		Account to = accounts.get(toAccountNum);
		from.withdraw(amount);
		to.deposit(amount);
		
		if (amount > 50000 && isFraud(fromAccountNum, toAccountNum, amount)) {
			from.setBlocked(true);
			to.setBlocked(true);
		}
	}
	
	/**
	 * TODO: реализовать метод. Возвращает остаток на счёте.
	 */
	
	public long getBalance(String accountNum) {
		return accounts.get(accountNum).getMoney();
	}
	
	public synchronized String addAccount(long amount) {
		
		Account account = new Account(COUNT.toString(), amount);
		accounts.put(account.getAccNumber(), account);
		COUNT.incrementAndGet();
		return account.getAccNumber();
	}
	
	public synchronized long getTotalMoneyPool() {
		long res = 0;
		for (Map.Entry<String, Account> a : accounts.entrySet()) {
			res += a.getValue().getMoney();
		}
		return res;
	}
}
