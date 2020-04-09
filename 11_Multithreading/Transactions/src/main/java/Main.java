
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class Main
{
	private static final int accountCount = 4;
	private static final int moneyInAcc = 10000;
	private static final int total = accountCount*moneyInAcc;
	public static void main(String[] args) {
		
		Bank b = new Bank();
		for (int i = 0; i < accountCount; i++) b.addAccount(moneyInAcc);
		
		ArrayList<Thread> threads = new ArrayList<>();
		for (int i = 0; i < Runtime.getRuntime().availableProcessors(); i++) {
			threads.add(new Thread(() -> {
				Random r = new Random();
				
				for (;;){
					String fromAcc = r.nextInt(3) + "";
					String toAcc = r.nextInt(3) + "";
					try {
						b.transfer(fromAcc, toAcc, 100);
					}catch (Exception ex) {
						System.out.println(ex.getMessage());
					}
					if (b.getTotalMoneyPool() != total) {
						System.out.println("Something gone wrong with money pool " + b.getTotalMoneyPool());
					}
				}
			}));
			
		}
		
		threads.forEach(Thread::start);
		Scanner s = new Scanner(System.in);
		s.nextLine();
		threads.forEach(Thread::interrupt);
	}
}
