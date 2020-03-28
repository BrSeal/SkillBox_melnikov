import Tables.Student;
import org.hibernate.Session;

public class Main
{
	public static void main(String[] args) {
		Session s = HibernateExp.getSession();
		Student t = s.get(Student.class,1);
        t.getCourses().forEach(System.out::println);
        HibernateExp.close();
	}
}
