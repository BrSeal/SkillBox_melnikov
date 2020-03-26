import Tables.Course;
import Tables.Student;
import org.hibernate.Session;

public class Main
{
	public static void main(String[] args) {
		Session s = HibernateExp.getSession();
		Course course = s.get(Course.class, 1);
		String courseData = course.getType() + " " + course.getDescription();
		
		Student student = s.get(Student.class, 12);
		
		System.out.println(courseData);
		System.out.println(student);
		
		HibernateExp.close();
	}
}
