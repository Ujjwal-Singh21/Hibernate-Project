package com.capg.HibernateNamedQuery;

import java.util.Iterator;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.TypedQuery;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;


@NamedQueries({ @NamedQuery(name = "findEmployeeByName", query = "from Employee6 e where e.name = :inputName") })
@Entity
@Table(name = "employee_hnq")
class Employee6 {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	int id;
	String name;
	String job;
	int salary;

	public Employee6() {
		super();
	}

	public Employee6(String name, String job, int salary) {
		super();
//		this.id = id;
		this.name = name;
		this.job = job;
		this.salary = salary;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getSalary() {
		return salary;
	}

	public void setSalary(int salary) {
		this.salary = salary;
	}

	public String getJob() {
		return job;
	}

	public void setJob(String job) {
		this.job = job;
	}

	public String toString() {
		return id + " " + name + " " + salary + " " + job;
	}

}

// Main Class
public class NamedQueryDemo {

	public static void main(String[] args) {

		Configuration cfg = new Configuration().configure().addAnnotatedClass(Employee6.class);
		SessionFactory sf = cfg.buildSessionFactory();
		Session session = sf.openSession();
		Transaction tx = session.beginTransaction();

		Employee6 emp1 = new Employee6("Ajay", "Developer", 2500);
		Employee6 emp2 = new Employee6("Sanjeev", "Singer", 3500);
		Employee6 emp3 = new Employee6("Karthik", "Actor", 6500);
		Employee6 emp4 = new Employee6("Karthik", "Farmer", 4500);
		Employee6 emp5 = new Employee6("Karthik", "Doctor", 1500);

		session.persist(emp1);
		session.persist(emp2);
		session.persist(emp3);
		session.persist(emp4);
		session.persist(emp5);
		
		// Hibernate Named Query
		TypedQuery<Employee6> query = session.getNamedQuery("findEmployeeByName");
		query.setParameter("inputName", "Karthik");

		List<Employee6> employees = query.getResultList();

		Iterator<Employee6> itr = employees.iterator();
		
		while (itr.hasNext()) {
			Employee6 e = itr.next();
			System.out.println(e);
		}
		
		tx.commit();
		session.close();
	}
}
