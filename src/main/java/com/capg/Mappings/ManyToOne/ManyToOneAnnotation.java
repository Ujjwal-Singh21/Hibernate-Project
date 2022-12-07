package com.capg.Mappings.ManyToOne;
import java.util.Iterator;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.TypedQuery;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

// Employee
@Entity
@Table(name = "emp107")
class Employee4 {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int employeeId;
	private String name, email;

	@ManyToOne(cascade = CascadeType.ALL)
	private Address4 address;

	public int getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(int employeeId) {
		this.employeeId = employeeId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Address4 getAddress() {
		return address;
	}

	public void setAddress(Address4 address) {
		this.address = address;
	}
}

// address
@Entity
@Table(name = "address107")
class Address4 {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int addressId;
	private String addressLine1, city, state, country;
	private int pincode;

	@OneToOne(cascade = CascadeType.ALL)
	private Employee4 employee;

	public int getAddressId() {
		return addressId;
	}

	public void setAddressId(int addressId) {
		this.addressId = addressId;
	}

	public String getAddressLine1() {
		return addressLine1;
	}

	public void setAddressLine1(String addressLine1) {
		this.addressLine1 = addressLine1;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public int getPincode() {
		return pincode;
	}

	public void setPincode(int pincode) {
		this.pincode = pincode;
	}

	public Employee4 getEmployee() {
		return employee;
	}

	public void setEmployee(Employee4 employee) {
		this.employee = employee;
	}
}

// Main class
public class ManyToOneAnnotation {

	public static void main(String[] args) {

		StandardServiceRegistry ssr = new StandardServiceRegistryBuilder().configure("hibernate.cfg.xml").build();
		Metadata meta = new MetadataSources(ssr).getMetadataBuilder().build();

		SessionFactory factory = meta.getSessionFactoryBuilder().build();
		Session session = factory.openSession();

		Transaction tx = session.beginTransaction();

		Employee4 e1 = new Employee4();
		e1.setName("Ravi Malik");
		e1.setEmail("ravi@gmail.com");

		Employee4 e2 = new Employee4();
		e2.setName("Anuj Verma");
		e2.setEmail("anuj@gmail.com");

		Address4 address1 = new Address4();
		address1.setAddressLine1("G-13,Sector 3");
		address1.setCity("Noida");
		address1.setState("UP");
		address1.setCountry("India");
		address1.setPincode(201301);

		e1.setAddress(address1);
		e2.setAddress(address1);

		session.persist(e1);
		session.persist(e2);

		// Fetch
		TypedQuery<Employee4> query = session.createQuery("from Employee4 e");

		List<Employee4> list = query.getResultList();

		Iterator<Employee4> itr = list.iterator();

		while (itr.hasNext()) {
			Employee4 emp = itr.next();
			System.out.println(emp.getEmployeeId() + " " + emp.getName() + " " + emp.getEmail());
			Address4 address = emp.getAddress();
			System.out.println(address.getAddressLine1() + " " + address.getCity() + " " + address.getState() + " "
					+ address.getCountry() + " " + address.getPincode());
		}

		tx.commit();

		session.close();
		System.out.println("Many To One Annotation - success");
	}
}
