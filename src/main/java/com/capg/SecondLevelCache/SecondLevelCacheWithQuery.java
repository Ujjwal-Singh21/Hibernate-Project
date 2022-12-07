package com.capg.SecondLevelCache;
import javax.persistence.TypedQuery;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

// - By default second level cache will work only with get()/load() methods
// - It will not work if we use Query(HQL) to fetch data.
// - To enable that we have to do 2 changes, 1st in cgg.xml file and 2nd below the line where Query is written.
// - <property name="hibernate.cache.use_query_cache"> true </property>
// - query.setCacheable(true);

// - Here we have used both Query(I)-dpricated and TypedQuery(I) which is new and Child class o Query(I)
// - Query(I) -> has list() method for getting many records,
//               and uniqueResult() for getting single record.

// - TYpedQuery(I) -> has getResultList() method for getting many records,
//               and getSingleResult() for getting single record.
//--------------------------------------------------------------------------------------------------------------
@SuppressWarnings("deprecation")
public class SecondLevelCacheWithQuery {

	public static void main(String[] args) {

		StandardServiceRegistry ssr = new StandardServiceRegistryBuilder().configure("hibernate.cfg.xml").build();
		Metadata meta = new MetadataSources(ssr).getMetadataBuilder().build();
		SessionFactory factory = meta.getSessionFactoryBuilder().build();
		Employee7 emp;

		// Session-1
		// -----------
		Session session1 = factory.openSession();

		Query<Employee7> q = session1.createQuery("from Employee7 e where e.id = 121");
		q.setCacheable(true);
		emp = (Employee7) q.uniqueResult();
		System.out.println(emp.getId() + " " + emp.getName() + " " + emp.getSalary());

		session1.close();

		// Session-2
		// ----------
		Session session2 = factory.openSession();

//		TypedQuery<Employee7> tq = session2.createQuery("from Employee7 e where e.id = 121");
//		emp = (Employee7) tq.getSingleResult();
		
		Query<Employee7> tq = session2.createQuery("from Employee7 e where e.id = 121");
		tq.setCacheable(true);
		emp = (Employee7) tq.getSingleResult();
		System.out.println(emp.getId() + " " + emp.getName() + " " + emp.getSalary());

		session2.close();
	}

}
