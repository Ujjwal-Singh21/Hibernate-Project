package com.capg.HibernateProject;
import java.util.Iterator;
import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

public class ManageEmployee {

	private static SessionFactory factory;

	public static void main(String[] args) {

		ManageEmployee manageEmployee = new ManageEmployee();

		try {
			Configuration configuration = new Configuration();
			configuration = configuration.configure().addAnnotatedClass(Employee.class);
			factory = configuration.buildSessionFactory();
		} catch (Exception e) {
			System.err.println("Failed to create sessionFactory object." + e);
		}

		/* Add few employee records in database */
		int empID1 = manageEmployee.addEmployee(101, "Ali", 1000);
		int empID2 = manageEmployee.addEmployee(102, "Das", 5000);
		int empID3 = manageEmployee.addEmployee(103, "Paul", 10000);
		int empID4 = manageEmployee.addEmployee(104, "Steve", 2200);
		int empID5 = manageEmployee.addEmployee(105, "Clarke", 6500);
		int empID6 = manageEmployee.addEmployee(106, "Diana", 6900);
		int empID7 = manageEmployee.addEmployee(107, "David", 8500);
		int empID8 = manageEmployee.addEmployee(108, "Virat", 12500);
		int empID9 = manageEmployee.addEmployee(109, "Rohit", 9800);
		
		/* List down all the employees */
		manageEmployee.listEmployees();

		/* Update employee's records */
		manageEmployee.updateEmployee(empID1, 5000);

		/* Delete an employee from the database */
		manageEmployee.deleteEmployee(empID2);

		/* List down new list of the employees */
		manageEmployee.listEmployees();
	}

	/* Method to CREATE an employee in the database */
	// ------------------------------------------------
	public Integer addEmployee(int empId, String empName, double empSalary) {

		Session session = factory.openSession();
		Transaction tx = null;
		Integer employeeID = null;

		try {
			tx = session.beginTransaction();
			Employee employee = new Employee(empId, empName, empSalary);
			employeeID = (Integer) session.save(employee);
			tx.commit();
		} catch (HibernateException e) {
			if (tx != null)
				tx.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}
		return employeeID;
	}

	/* Method to UPDATE salary for an employee */
	// -------------------------------------------
	public void updateEmployee(Integer EmployeeID, int salary) {

		Session session = factory.openSession();
		Transaction tx = null;

		try {
			tx = session.beginTransaction();
			Employee employee = (Employee) session.get(Employee.class, EmployeeID);
			employee.setEmpSalary(salary);
			session.update(employee);
			tx.commit();
		} catch (Exception e) {
			if (tx != null)
				tx.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}
	}

	/* Method to DELETE an employee from the records */
	// -------------------------------------------------
	public void deleteEmployee(Integer EmployeeID) {

		Session session = factory.openSession();
		Transaction tx = null;

		try {
			tx = session.beginTransaction();
			Employee employee = (Employee) session.get(Employee.class, EmployeeID);
			session.delete(employee);
			tx.commit();
		} catch (HibernateException e) {
			if (tx != null)
				tx.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}
	}

	/* Method to READ all the employees */
	// ------------------------------------
	public void listEmployees() {

		Session session = factory.openSession();
		Transaction tx = null;

		try {
			tx = session.beginTransaction();
			 Query<Employee> query = session.createQuery("FROM Employee");
			 List<Employee> employees = query.list();

			Iterator<Employee> iterator = employees.iterator();
			while (iterator.hasNext()) {
				Employee emp = iterator.next();
				System.out.println(emp);
			}

			tx.commit();
		} catch (HibernateException e) {
			if (tx != null)
				tx.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}
	}
}
