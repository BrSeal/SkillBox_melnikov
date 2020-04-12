public class WhileCycle
{
	
	public static void main(String[] args) {
		int i = 200000;
		while (i <= 235000) {
            if (i >= 210000 && i < 220000) { continue; }
			System.out.println(i++);
		}
	}
}