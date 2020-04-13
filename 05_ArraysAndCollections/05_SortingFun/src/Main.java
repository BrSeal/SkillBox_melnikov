import java.util.*;

public class Main
{
	public static void main(String[] args) {
		ArrayList<String> list = new ArrayList<>();
		stringBuilderFill(list);
		
		infiniteSearch(list);
	}
	
	private static void stringBuilderFill(ArrayList<String> list) {
		StringBuilder b = new StringBuilder("XnnnYZ000");
		String[] letters = { "A", "B", "C", "E", "H", "K", "M", "O", "P", "T", "X", "Y" };
		String[] nums = { "000", "111", "222", "333", "444", "555", "666", "777", "888", "999" };
		for (String letter1 : letters) {
			// replace X
			b.replace(0, 1, letter1);
			
			//replace nnn
			for (String num : nums) {
				b.replace(1, 4, num);
				
				//replace Y
				for (String letter2 : letters) {
					b.replace(4, 5, letter2);
					
					//replace Z
					for (String letter3 : letters) {
						b.replace(5, 6, letter3);
						
						//replace 000
						int i = 1;
						while (i < 10) {
							b.replace(8, 9, i++ + "");
							list.add(b.toString());
						}
						while (i < 100) {
							b.replace(7, 9, i++ + "");
							list.add(b.toString());
						}
						while (i < 199) {
							b.replace(6, 9, i++ + "");
							list.add(b.toString());
						}
						b.replace(6, 9, "000");
					}
				}
			}
		}
	}
	
	private static void infiniteSearch(ArrayList<String> list) {
		HashSet<String> hashSet = new HashSet<>(list);
		TreeSet<String> treeSet = new TreeSet<>(list);
		
		Scanner scanner = new Scanner(System.in);
		String in;
		long start;
		
		for (; ; ) {
			System.out.println("Введите номер:");
			in = scanner.nextLine();
			
			start = System.nanoTime();
			System.out.println("Прямой перебор: " + (list.contains(in) ? "Найдено! " : "Не найдено! ") + (System.nanoTime() - start) + " нс");
			
			start = System.nanoTime();
			System.out.println("Бинарный поиск: " + (Collections.binarySearch(list, in) > -1 ? "Найдено! " : "Не найдено! ") + (System.nanoTime() - start) + " нс");
			
			start = System.nanoTime();
			System.out.println("Поиск по HashSet: " + (hashSet.contains(in) ? "Найдено! " : "Не найдено! ") + (System.nanoTime() - start) + " нс");
			
			start = System.nanoTime();
			System.out.println("Поиск по TreeSet: " + (treeSet.contains(in) ? "Найдено! " : "Не найдено! ") + (System.nanoTime() - start) + " нс");
		}
	}
}
