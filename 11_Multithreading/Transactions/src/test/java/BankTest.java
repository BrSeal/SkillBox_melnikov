import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

class BankTest
{
	
	@Test
	void securityTest(){
		Bank b = new Bank();
		for(int i=0;i<100;i++) b.addAccount(10000);
		
		ArrayList<Thread> threads=new ArrayList<>();
		for(int i=0;i<Runtime.getRuntime().availableProcessors();i++) {
		threads.add(new Thread(()-> {
			Random r=new Random();
			
			for(int qwe=0;qwe<100000;qwe++) {
				String fromAcc=r.nextInt(99)+"";
				String toAcc=r.nextInt(99)+"";
				try {
					
					b.transfer(fromAcc, toAcc, 100);
					Thread.sleep(0);
				}catch (Exception ex){
					System.out.println(ex.getMessage());
				}
				if (b.getTotalMoneyPool() != 1000000) { System.out.println("Something gone wrong with money pool "+b.getTotalMoneyPool()
				); }
			}}));
		
		}
		
		threads.forEach(Thread::start);
		Scanner s=new Scanner(System.in);
		s.nextLine();
		threads.forEach(Thread::interrupt);
	}
}