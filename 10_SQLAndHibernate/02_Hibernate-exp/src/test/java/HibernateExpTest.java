import Tables.*;
import Tables.PrimaryKeys.LinkedPurchaseListPK;
import Tables.PrimaryKeys.SubscriptionPK;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.Date;
import java.util.List;

class HibernateExpTest
{
	static Session s;
	
	@BeforeAll
	static void init() {
		s = HibernateExp.getSession();
	}
	
	@AfterAll
	static void end() {
		HibernateExp.close();
	}
	
	@Test
	void getInfoFromTable() {
		Student t = s.get(Student.class, 1);
		Course c = s.get(Course.class, 1);
		t.getCourses().forEach(System.out::println);
		c.getStudents().forEach(System.out::println);
	}
	
	@Test
	void compositeKey() {
		Student student = s.get(Student.class, 1);
		Course course = s.get(Course.class, 2);
		Subscription sub = s.get(Subscription.class, new SubscriptionPK(student, course));
		System.out.println(sub);
	}
	
	@Test
	void getInfoViaQuery() {
		CriteriaBuilder builder = s.getCriteriaBuilder();
		CriteriaQuery<Course> query = builder.createQuery(Course.class);
		Root<Course> root = query.from(Course.class);
		query.select(root);
		
		List<Course> list = s.createQuery(query).getResultList();
		
		list.forEach(System.out::println);
	}
	
	@Test
	void complexQuery() {
    	/*
    	SELECT name
        FROM courses
        WHERE price >100_000
        ORDER BY price DESC
        LIMIT 5
         */
		CriteriaBuilder builder = s.getCriteriaBuilder();
		CriteriaQuery<Course> query = builder.createQuery(Course.class);
		Root<Course> root = query.from(Course.class);
		query.select(root).where(builder.greaterThan(root.get("price"), 100_000)).orderBy(builder.desc(root.get("price")));
		
		List<Course> list = s.createQuery(query).setMaxResults(5).getResultList();
		
		list.forEach(System.out::println);
	}
	
	@Test
	void hqlQuery() {
    	/*
    	SELECT name
        FROM courses
        WHERE price >100_000
        ORDER BY price DESC
        LIMIT 5
         */
		String hqlQuery="FROM "+ Course.class.getSimpleName()+
				" WHERE price>100000"+
				" ORDER BY price DESC ";
		List<Course> list = s.createQuery(hqlQuery).setMaxResults(5).getResultList();
		
		list.forEach(System.out::println);
	}
	
	@Test
	void FillNewTable() {
		try {
			String hqlQuery = "FROM " + PurchaseList.class.getSimpleName();
			List<PurchaseList> list = s.createQuery(hqlQuery).getResultList();
			
			Transaction transaction = s.beginTransaction();
			list.forEach(pl -> {
				String courseQuery = "FROM " + Course.class.getSimpleName() + " WHERE name LIKE '" + pl.getCourse_name() + "'";
				Course course = (Course) s.createQuery(courseQuery).getResultList().get(0);
				
				String studentQuery = "FROM " + Student.class.getSimpleName() + " WHERE name LIKE '" + pl.getStudent_name() + "'";
				Student student = (Student) s.createQuery(studentQuery).getResultList().get(0);
				
				Integer price = pl.getPrice();
				Date subscription_date = pl.getSubscription_date();
				
				LinkedPurchaseListPK key = new LinkedPurchaseListPK(student, course);
				s.save(new LinkedPurchaseList(key, price, subscription_date));
			});
			transaction.commit();
		}catch (Exception e){
			e.getMessage();
		}
	}
}