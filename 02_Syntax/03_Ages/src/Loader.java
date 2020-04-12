/**
 * В новом проекте создать переменные с возрастами трёх человек и ещё три переменные,
 * в которые нужно будет записать минимальный, максимальный и средний между максимальным
 * и минимальным возрастом. Сделать это так, как показано на видео, а затем написать код,
 * который будет сравнивать возраста и записывать их в соответствующие переменные.
 * Результат: независимо от возрастов, которые указаны в первых трёх переменных,
 * в консоли возраста должны быть обозначены верно: минимальный, средний и максимальный.
 */

public class Loader
{
	
	private static int aliceAge = 1;
	private static int bobAge = 2;
	private static int cherylAge = 3;
	
	private static int min, max, middle;
	
	public static void main(String[] args) {
		sortAges();
		System.out.printf("Минимальный возраст: %d\n" + "Средний возраст: %d\n" + "Максимальный возраст: %d\n", min, middle, max);
	}
	
	private static void sortAges() {
		boolean aliceOlderBob = aliceAge >= bobAge;
		boolean aliceOlderCheryl = aliceAge >= cherylAge;
		boolean bobOlderCheryl = bobAge >= cherylAge;
		
		if (aliceOlderBob && aliceOlderCheryl) {
			max = aliceAge;
			if (bobOlderCheryl) {
				middle = bobAge;
				min = cherylAge;
			}
			else {
				middle = cherylAge;
				min = bobAge;
			}
		}
		else if (!aliceOlderBob && bobOlderCheryl) {
			max = bobAge;
			if (aliceOlderCheryl) {
				middle = aliceAge;
				min = cherylAge;
			}
			else {
				middle = cherylAge;
				min = aliceAge;
			}
		}
		else if (!aliceOlderCheryl && !bobOlderCheryl) {
			max = cherylAge;
			if (!aliceOlderBob) {
				middle = bobAge;
				min = aliceAge;
			}
			else {
				middle = aliceAge;
				min = bobAge;
			}
		}
	}
}
