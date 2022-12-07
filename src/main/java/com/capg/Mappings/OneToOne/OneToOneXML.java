package com.capg.Mappings.OneToOne;
import java.util.Iterator;
import java.util.List;
import javax.persistence.TypedQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;


// Employee
class Employee1 {

	private int employeeId;
	private String name, email;
	private Address1 address;

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

	public Address1 getAddress() {
		return address;
	}

	public void setAddress(Address1 address) {
		this.address = address;
	}

}

//Address
class Address1 {
	
	private int addressId;
	private String addressLine1, city, state, country;
	private int pincode;
	private Employee1 employee;

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

	public Employee1 getEmployee() {
		return employee;
	}

	public void setEmployee(Employee1 employee) {
		this.employee = employee;
	}

}

// Main Class
public class OneToOneXML {

	public static void main(String[] args) {

		StandardServiceRegistry ssr = new StandardServiceRegistryBuilder().configure("hibernate.cfg.xml").build();
		Metadata meta = new MetadataSources(ssr).getMetadataBuilder().build();

		SessionFactory factory = meta.getSessionFactoryBuilder().build();
		Session session = factory.openSession();

		Transaction tx = session.beginTransaction();

		Employee1 e1 = new Employee1();
		e1.setName("Ravi Malik");
		e1.setEmail("ravi@gmail.com");

		Address1 address1 = new Address1();
		address1.setAddressLine1("G-21,Lohia nagar");
		address1.setCity("Ghaziabad");
		address1.setState("UP");
		address1.setCountry("India");
		address1.setPincode(201301);

		e1.setAddress(address1);
		address1.setEmployee(e1);

		session.persist(e1);

		//To Fetch
		TypedQuery<Employee1> query = session.createQuery("from Employee1 e");

		List<Employee1> list = query.getResultList();

		Iterator<Employee1> itr = list.iterator();

		while (itr.hasNext()) {

			Employee1 emp = itr.next();
			System.out.println(emp.getEmployeeId() + " " + emp.getName() + " " + emp.getEmail());
			
			Address1 address = emp.getAddress();
			
			System.out.println(address.getAddressLine1() + " " + address.getCity() + " " + address.getState() + " "
					+ address.getCountry() + " " + address.getPincode());
		}

		tx.commit();
		session.close();
		System.out.println("OneToOne XML - success");
	}
}
