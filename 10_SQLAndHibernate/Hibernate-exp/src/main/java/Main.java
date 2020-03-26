import Tables.Course;
import org.hibernate.Session;

public class Main {
    public static void main(String[] args) {
        HibernateExp h=HibernateExp.getInstance();
        Session s=h.getSession();
        String course=s.get(Course.class,1).getName();
        System.out.println(course);
    }
}
