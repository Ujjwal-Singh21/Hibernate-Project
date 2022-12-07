package com.capg.Mappings.ManyToOne;
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
class Employee3 {

	private int employeeId;
	private String name, email;
	private Address3 address;

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

	public Address3 getAddress() {
		return address;
	}

	public void setAddress(Address3 address) {
		this.address = address;
	}

}

//Answer
class Address3 {

	private int addressId;
	private String addressLine1, city, state, country;
	private int pincode;
	private Employee3 employee;

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

	public Employee3 getEmployee() {
		return employee;
	}

	public void setEmployee(Employee3 employee) {
		this.employee = employee;
	}

}

//Main class
public class ManyToOneXML {

	public static void main(String[] args) {

		StandardServiceRegistry ssr = new StandardServiceRegistryBuilder().configure("hibernate.cfg.xml").build();
		Metadata meta = new MetadataSources(ssr).getMetadataBuilder().build();

		SessionFactory factory = meta.getSessionFactoryBuilder().build();
		Session session = factory.openSession();

		Transaction tx = session.beginTransaction();

		Employee3 e1 = new Employee3();
		e1.setName("Ravi Malik");
		e1.setEmail("ravi@gmail.com");

		Employee3 e2 = new Employee3();
		e2.setName("Anuj Verma");
		e2.setEmail("anuj@gmail.com");

		Address3 address1 = new Address3();
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
		TypedQuery<Employee3> query = session.createQuery("from Employee3 e");
		List<Employee3> list = query.getResultList();

		Iterator<Employee3> itr = list.iterator();
		
		while (itr.hasNext()) {
			Employee3 emp = itr.next();
			System.out.println(emp.getEmployeeId() + " " + emp.getName() + " " + emp.getEmail());
			Address3 address = emp.getAddress();
			System.out.println(address.getAddressLine1() + " " + address.getCity() + " " + address.getState() + " "
					+ address.getCountry() + " " + address.getPincode());
		}

		tx.commit();
		session.close();
		System.out.println("Many To One Annotation - success");
	}
}
