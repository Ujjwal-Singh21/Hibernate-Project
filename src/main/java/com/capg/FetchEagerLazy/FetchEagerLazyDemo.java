package com.capg.FetchEagerLazy;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

// Alien
@Entity
@Table(name = "alien")
class Alien {

	@Id
	private int aliendId;
	private String alienName;
	@OneToMany(mappedBy ="alien", fetch = FetchType.EAGER)
	private Collection<Laptop> laptops = new ArrayList<Laptop>();

	public int getAliendId() {
		return aliendId;
	}

	public void setAliendId(int aliendId) {
		this.aliendId = aliendId;
	}

	public String getAlienName() {
		return alienName;
	}

	public void setAlienName(String alienName) {
		this.alienName = alienName;
	}

	public Collection<Laptop> getLaptops() {
		return laptops;
	}

	public void setLaptops(Collection<Laptop> laps) {
		this.laptops = laps;
	}
}

//Laptop
@Entity
@Table(name = "laptop")
class Laptop {

	@Id
	private int laptopId;
	private String laptopBrand;
	private int price;

	@ManyToOne
	private Alien alien;

	public int getLaptopId() {
		return laptopId;
	}

	public void setLaptopId(int laptopId) {
		this.laptopId = laptopId;
	}

	public String getLaptopBrand() {
		return laptopBrand;
	}

	public void setLaptopBrand(String laptopBrand) {
		this.laptopBrand = laptopBrand;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public Alien getAlien() {
		return alien;
	}

	public void setAlien(Alien alien) {
		this.alien = alien;
	}

	@Override
	public String toString() {
		return "Laptop [laptopId=" + laptopId + ", laptopBrand=" + laptopBrand + ", price=" + price + "]";
	}
}

// Main class
public class FetchEagerLazyDemo {

	public static void main(String[] args) {

		Configuration cfg = new Configuration().configure().addAnnotatedClass(Alien.class)
				.addAnnotatedClass(Laptop.class);
		SessionFactory sf = cfg.buildSessionFactory();
		Session session = sf.openSession();
		Transaction tx = session.beginTransaction();

		Alien a1 = new Alien();
		a1.setAliendId(1);
		a1.setAlienName("Navin");

		Alien a2 = new Alien();
		a2.setAliendId(2);
		a2.setAlienName("Rahul");

		Alien a3 = new Alien();
		a3.setAliendId(3);
		a3.setAlienName("Mayank");

		// Laptop
		Laptop l1 = new Laptop();
		l1.setLaptopId(101);
		l1.setLaptopBrand("Dell");
		l1.setPrice(1000);
		

		Laptop l2 = new Laptop();
		l2.setLaptopId(102);
		l2.setLaptopBrand("Apple");
		l2.setPrice(2000);
		

		Laptop l3 = new Laptop();
		l3.setLaptopId(103);
		l3.setLaptopBrand("Asus");
		l3.setPrice(800);
		

		Laptop l4 = new Laptop();
		l4.setLaptopId(104);
		l4.setLaptopBrand("Acer");
		l4.setPrice(1500);
		

		Laptop l5 = new Laptop();
		l5.setLaptopId(105);
		l5.setLaptopBrand("Samsung");
		l5.setPrice(1400);
		
		
		List<Laptop> laptopList1 = new ArrayList<Laptop>();
		laptopList1.add(l1);
		laptopList1.add(l3);
		laptopList1.add(l5);
		
		List<Laptop> laptopList2 = new ArrayList<Laptop>();
		laptopList2.add(l2);
		laptopList2.add(l4);
		
		a1.setLaptops(laptopList1);
		a3.setLaptops(laptopList2);
		
		l1.setAlien(a1);
		l2.setAlien(a3);
		l3.setAlien(a1);
		l4.setAlien(a3);
		l5.setAlien(a1);
		
//		session.persist(a1);
//		session.persist(a2);
//		session.persist(a3);
//		
//		session.persist(l1);
//		session.persist(l2);
//		session.persist(l3);
//		session.persist(l4);
//		session.persist(l5);
		
		Alien alien1 = session.get(Alien.class, 1);
		System.out.println(alien1.getAlienName());
		
//		for(Laptop lap : alien1.getLaptops()) {
//			System.out.println(lap);
//		}
		
		tx.commit();
		session.close();
	}

}
