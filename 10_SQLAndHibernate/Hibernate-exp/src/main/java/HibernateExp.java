import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

public class HibernateExp
{
	private static Session session;
	private static SessionFactory sessionFactory;
	private static HibernateExp instance;
	
	private HibernateExp() {
		init();
	}
	
	public static Session getSession() {
		if (instance == null) { instance = new HibernateExp(); }
		return session;
	}
	
	private void init() {
		StandardServiceRegistry reg = new StandardServiceRegistryBuilder().configure("hibernate.cfg.xml").build();
		Metadata metadata = new MetadataSources(reg).getMetadataBuilder().build();
		sessionFactory = metadata.getSessionFactoryBuilder().build();
		session = sessionFactory.openSession();
	}
	
	public static void close() {
		session.close();
		sessionFactory.close();
	}
}
