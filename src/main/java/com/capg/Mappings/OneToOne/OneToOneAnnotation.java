package com.capg.Mappings.OneToOne;
import java.util.Iterator;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import javax.persistence.TypedQuery;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

@Entity
@Table(name = "emp220")
class Employee2 {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
//	@PrimaryKeyJoinColumn
	private int employeeId;
	private String name, email;
	
	// @PrimaryKeyJoinColumn -> it may be used in a OneToOne mapping in which the primary key of the referencing entity is 
	// used as a foreign key to the referenced entity
	
	@OneToOne(targetEntity = Address2.class, cascade = CascadeType.ALL)
	private Address2 address;

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

	public Address2 getAddress() {
		return address;
	}

	public void setAddress(Address2 address) {
		this.address = address;
	}

}

// Answer
@Entity
@Table(name = "address220")
class Address2 {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int addressId;
	private String addressLine1, city, state, country;
	private int pincode;

	@OneToOne(targetEntity = Employee2.class)
	private Employee2 employee;

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

	public Employee2 getEmployee() {
		return employee;
	}

	public void setEmployee(Employee2 employee) {
		this.employee = employee;
	}
}

//Main class
public class OneToOneAnnotation {

	public static void main(String[] args) {

		StandardServiceRegistry ssr = new StandardServiceRegistryBuilder().configure("hibernate.cfg.xml").build();
		Metadata meta = new MetadataSources(ssr).getMetadataBuilder().build();

		SessionFactory factory = meta.getSessionFactoryBuilder().build();
		Session session = factory.openSession();

		Transaction tx = session.beginTransaction();

		Employee2 e1 = new Employee2();
		e1.setName("Ravi Malik");
		e1.setEmail("ravi@gmail.com");

		Address2 address1 = new Address2();
		address1.setAddressLine1("G-21,Lohia nagar");
		address1.setCity("Ghaziabad");
		address1.setState("UP");
		address1.setCountry("India");
		address1.setPincode(201301);

		e1.setAddress(address1);
		address1.setEmployee(e1);

		session.persist(e1);
		session.persist(address1);

		//Fetch
		TypedQuery<Employee2> query = session.createQuery("from Employee2");
		
		List<Employee2> list = query.getResultList();

		Iterator<Employee2> itr = list.iterator();
		
		while (itr.hasNext()) {
			Employee2 emp = itr.next();
			System.out.println(emp.getEmployeeId() + " " + emp.getName() + " " + emp.getEmail());
			Address2 address = emp.getAddress();
			System.out.println(address.getAddressLine1() + " " + address.getCity() + " " + address.getState() + " "
					+ address.getCountry() + " " + address.getPincode());
		}
		
		tx.commit();
		session.close();
		System.out.println("One To One Annotation-success");
	}
}
