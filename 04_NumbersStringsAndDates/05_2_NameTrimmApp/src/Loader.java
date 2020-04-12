import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Loader
{
	
	public static void main(String[] args) throws IOException {
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		String name;
		String[] names;
		while (true) {
			name = reader.readLine().trim();
			names = name.split("\\s");
                if (names.length > 1 && names.length < 4) { break; }
			System.out.println("Введите ФИО через пробел. ");
		}
		
		
		System.out.println("Фамилия: " + names[0]);
		System.out.println("Имя: " + names[1]);
		if (names.length == 3) {
			System.out.println("Отчество: " + names[2]);
		}
	}
}
