import Exceptions.BlockedAccountException;
import Exceptions.NoEnoughMoneyException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


import java.util.ArrayList;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class BankTest
{
	Bank b;
	int accCount = 100;
	int threadCount = 10;
	int transactionsPerThread = 100000;
	int transferLimit=50100;
	@BeforeEach
	void init(){
		b=new Bank();
		b.addAccount(10000);
		b.addAccount(10000);
	}
	@Test
	void securityTest() throws InterruptedException {
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
	
	@Test
	void transferFromBlocked0(){
		b.getAccount("0").setBlocked(true);
		assertThrows(BlockedAccountException.class,()->{
			b.transfer("0","1",1000);
		});
		assertEquals(b.getBalance("0"),10000);
		assertEquals(b.getBalance("1"),10000);
	}
	
	@Test
	void transferToBlocked(){
		b.getAccount("1").setBlocked(true);
		assertThrows(BlockedAccountException.class,()->{
			b.transfer("0","1",1000);
		});
		assertEquals(b.getBalance("0"),10000);
		assertEquals(b.getBalance("1"),10000);
	}
	
	@Test
	void transferBiggerAmountOfMoney(){
		assertThrows(NoEnoughMoneyException.class,()->{
			b.transfer("0","1",20000);
		});
		assertEquals(b.getBalance("0"),10000);
		assertEquals(b.getBalance("1"),10000);
	}
	
	@Test
	void transferFromBlocked1(){
		b.getAccount("0 ").setBlocked(true);
		assertThrows(BlockedAccountException.class,()->{
			b.transfer("0","1",1000000);
		});
		assertEquals(b.getBalance("0"),10000);
		assertEquals(b.getBalance("1"),10000);
	}
	
	
}