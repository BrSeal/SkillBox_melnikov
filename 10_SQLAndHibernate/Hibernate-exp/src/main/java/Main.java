import Tables.PurchaseList;
import Tables.Student;
import org.hibernate.Session;

public class Main
{
	public static void main(String[] args) {
		Session s = HibernateExp.getSession();
		PurchaseList t = s.get(PurchaseList.class,1);
        
        System.out.println(t.getCourse_name());
        HibernateExp.close();
	}
}
