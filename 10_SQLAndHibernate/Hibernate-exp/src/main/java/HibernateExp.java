import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

public class HibernateExp {
    private static Session session;

    public static Session getSession() {
        return session;
    }

    private static SessionFactory sessionFactory;

private static HibernateExp instance;
    private HibernateExp(){
        init();
    }

    public static HibernateExp getInstance(){
        if(instance==null) return instance=new HibernateExp();
        return instance;
    }

    private void init(){
        StandardServiceRegistry reg=new StandardServiceRegistryBuilder()
                .configure("hibernate.cfg.xml").build();

        Metadata metadata=new MetadataSources(reg)
                .getMetadataBuilder()
                .build();

       sessionFactory=metadata.getSessionFactoryBuilder().build();
       session=sessionFactory.openSession();
    }

    private void close(){
        session.close();
        sessionFactory.close();
    }
}
