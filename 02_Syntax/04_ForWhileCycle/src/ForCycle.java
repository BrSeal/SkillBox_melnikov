

public class ForCycle
{
	
	public static void main(String[] args) {
		
		for (int i = 200000; i <= 235000; i++) {
            if (i >= 210000 && i < 220000) { continue; }
			System.out.println(i);
		}
	}
}
