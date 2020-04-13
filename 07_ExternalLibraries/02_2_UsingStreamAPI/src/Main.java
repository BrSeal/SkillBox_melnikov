import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.*;

//В проекте с сотрудниками с помощью Stream API рассчитать максимальную зарплату тех,
// кто пришёл в 2017 году.
public class Main
{
	private static String staffFile = "res//staff.txt";
	private static String dateFormat = "dd.MM.yyyy";
	
	public static void main(String[] args) {
		Calendar c = Calendar.getInstance();
		c.set(2016, Calendar.DECEMBER, 31);
		
		Date after = c.getTime();
		c.set(2018, Calendar.JANUARY, 0);
		Date before = c.getTime();
		ArrayList<Employee> staff = loadStaffFromFile();
		staff.stream()
                .filter(e -> e.getWorkStart().after(after) && e.getWorkStart().before(before))
                .max(Comparator.comparing(Employee::getSalary))
                .ifPresent(System.out::println);
	}
	
	private static ArrayList<Employee> loadStaffFromFile() {
		ArrayList<Employee> staff = new ArrayList<>();
		try {
			List<String> lines = Files.readAllLines(Paths.get(staffFile));
			for (String line : lines) {
				String[] fragments = line.split("\t");
				if (fragments.length != 3) {
					System.out.println("Wrong line: " + line);
					continue;
				}
				staff.add(new Employee(fragments[0], Integer.parseInt(fragments[1]), (new SimpleDateFormat(dateFormat)).parse(fragments[2])));
			}
		}catch (Exception ex) {
			ex.printStackTrace();
		}
		return staff;
	}
}