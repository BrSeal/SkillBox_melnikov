import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.assertEquals;

class BankTest
{
	@Test
	void securityTest() throws InterruptedException {
		int accCount = 100;
		int threadCount = 10;
		int transactionsPerThread = 100000;
		int transferLimit=50100;
		
		Bank b = new Bank();
		for (int i = 0; i < accCount; i++) b.addAccount(10000);
		long totalCash = b.getTotalMoneyPool();
		
		ArrayList<Thread> threads = new ArrayList<>();
		for (int i = 0; i < threadCount; i++) {
			threads.add(new Thread(() -> {
				Random r = new Random();
				for (int j = 0; j < transactionsPerThread; j++) {
					String fromAcc = r.nextInt(accCount-1) + "";
					String toAcc = r.nextInt(accCount-1) + "";
					try {
						b.transfer(fromAcc, toAcc, r.nextInt(transferLimit));
					}catch (Exception e) {}
				}
			}));
		}
		
		threads.forEach(Thread::start);
		for (Thread thread : threads) thread.join();
		
		assertEquals(totalCash, b.getTotalMoneyPool());
	}
	
	@Test
	void accCreateTest() throws Exception {
		int threadCount = 100;
		int accsPerThread = 10000;
		int expected = threadCount * accsPerThread;
		
		
		Bank b = new Bank();
		ArrayList<Thread> threads = new ArrayList<>();
		for (int i = 0; i < threadCount; i++) {
			threads.add(new Thread(() -> {
				Random r = new Random();
				for (int j = 0; j < accsPerThread; j++) {
					b.addAccount(10000 + r.nextInt(10000));
				}
			}));
		}
		
		threads.forEach(Thread::start);
		for (Thread thread : threads) thread.join();
		assertEquals(expected, b.getAccounts().size());
	}
}