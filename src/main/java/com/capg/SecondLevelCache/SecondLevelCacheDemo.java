package com.capg.SecondLevelCache;
import javax.persistence.Cacheable;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;


// -> Hibernate second level cache uses a common cache for all the session object of a session factory. 
// -> It is useful if you have multiple session objects from a session factory.
// -> SessionFactory holds the second level cache data. It is global for all the session objects and not enabled by default.
// -> Different vendors have provided the implementation of Second Level Cache.

// 1) EH Cache 2) OS Cache 3) Swarm Cache 4) JBoss Cache
// Each implementation provides different cache usage functionality. There are four ways to use second level cache.

// - read-only: caching will work for read only operation.
// - nonstrict-read-write: caching will work for read and write but one at a time.
// - read-write: caching will work for read and write, can be used simultaneously.
// - transactional: caching will work for transaction.
// The cache-usage property can be applied to class or collection level in hbm.xml file.
// <cache usage="read-only" />  
//---------------------------------------------------------------------------------------------------------------------------

@Entity
@Table(name = "emp_cache")
@Cacheable
@Cache(usage = CacheConcurrencyStrategy.READ_ONLY)
class Employee7 {

	@Id
	private int id;
	private String name;
	private float salary;

	public Employee7() {
		super();
	}

	public Employee7(int id, String name, float salary) {
		super();
		this.id = id;
		this.name = name;
		this.salary = salary;
	}

	public int getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public float getSalary() {
		return salary;
	}
}

public class SecondLevelCacheDemo {

	public static void main(String[] args) {

		StandardServiceRegistry ssr = new StandardServiceRegistryBuilder().configure("hibernate.cfg.xml").build();
		Metadata meta = new MetadataSources(ssr).getMetadataBuilder().build();

		SessionFactory factory = meta.getSessionFactoryBuilder().build();

		// Session-1
		//----------
		Session session1 = factory.openSession();
		Transaction tx = session1.beginTransaction();

		Employee7 employee1 = new Employee7(121, "Ajay", 2500);
		Employee7 employee2 = new Employee7(122, "Sanjeev", 3500);

		session1.persist(employee1);
		session1.persist(employee2);

		tx.commit();
		session1.close();

		// Session-2
		//-----------
		Session session2 = factory.openSession();
		Employee7 emp1 = (Employee7) session2.load(Employee7.class, 121);
		System.out.println(emp1.getId() + " " + emp1.getName() + " " + emp1.getSalary());
		session2.close();

		// Session-3
		//----------
		Session session3 = factory.openSession();
		Employee7 emp2 = (Employee7) session3.load(Employee7.class, 121);
		System.out.println(emp2.getId() + " " + emp2.getName() + " " + emp2.getSalary());
		session3.close();

		// Note: As we can see here, hibernate does not fire query twice. If you don't use second level cache,
		// hibernate will fire query twice because both query uses different session objects.

	}
}
