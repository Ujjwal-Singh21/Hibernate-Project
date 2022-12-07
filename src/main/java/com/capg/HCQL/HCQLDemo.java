package com.capg.HCQL;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;

@Entity
@Table(name="employee_hcql")
class Employee5 {

	@Id
	private int employeeId;
	private String employeeName;
	private double empSalary;
	
	public Employee5() {
		super();
	}

	public Employee5(int employeeId, String employeeName, double empSalary) {
		super();
		this.employeeId = employeeId;
		this.employeeName = employeeName;
		this.empSalary = empSalary;
	}

	public int getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(int employeeId) {
		this.employeeId = employeeId;
	}

	public String getEmployeeName() {
		return employeeName;
	}

	public void setEmployeeName(String employeeName) {
		this.employeeName = employeeName;
	}

	public double getEmpSalary() {
		return empSalary;
	}

	public void setEmpSalary(double empSalary) {
		this.empSalary = empSalary;
	}

	@Override
	public String toString() {
		return "Employee5 [employeeId=" + employeeId + ", employeeName=" + employeeName + ", empSalary=" + empSalary + "]";
	}
}

// - The Hibernate Criteria Query Language (HCQL) is used to fetch the records based on the specific criteria. 
// - The Criteria interface provides methods to apply criteria such as retreiving all the records of table whose salary is 
//   greater than 50000 etc.
//---------------------------------------------------------------------------------------------------------------------------
public class HCQLDemo {

	public static void main(String[] args) {

		Configuration cfg = new Configuration().configure().addAnnotatedClass(Employee5.class);
		SessionFactory sf = cfg.buildSessionFactory();
		Session session = sf.openSession();
		Transaction tx = session.beginTransaction();

		for (int i = 1; i <= 20; i++) {
			Employee5 emp = new Employee5(i, "Employee-" +i, i * 1000.0);
			session.persist(emp);
		}
		
//		// Fetch based on HCQL criteria
		Criteria criteria = session.createCriteria(Employee5.class);
		
		// 1) Example of HCQL to get all the records
		List<Employee5> allEmployeesRecord = criteria.list();
		System.out.println("Example of HCQL to get all the records: " + allEmployeesRecord);

	
		// 2) Example of HCQL to get the 10th to 20th record 	
//		criteria.setFirstResult(10);
//		criteria.setMaxResults(20);
//		List<Employee5> list2 = criteria.list();
//		System.out.println("Example of HCQL to get the 10th to 20th record " + list2);
		
		
		// 3) Example of HCQL to get the records whose salary is greater than 10000
//		criteria.add(Restrictions.gt("empSalary", 10000.0));
//		List<Employee5> list3 = criteria.list();
//		System.out.println("Example of HCQL to get the records whose salary is greater than 10000: " + list3);
		
		// 4) Example of HCQL to get the records in ascending order on the basis of salary
		criteria.addOrder(Order.desc("empSalary"));
		List<Employee5> ascList = criteria.list();
		System.out.println("Example of HCQL to get the records in descending order on the basis of salary: " + ascList);
		
		// 6) HCQL with Projection
		// We can fetch data of a particular column by projection
		// such as name etc. Let's see the simple example of projection that prints data of NAME column of the table only.
		criteria.setProjection(Projections.property("employeeName"));
		List<Employee5> projectionList = criteria.list();
		System.out.println("Projection: " + projectionList);
		
		
		tx.commit();
		session.close();
		System.out.println("HCQL Demo success");

	}
}
