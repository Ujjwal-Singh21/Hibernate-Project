package com.capg.Mappings.OneToMany;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OrderColumn;
import javax.persistence.Table;
import javax.persistence.TypedQuery;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

// QuestionClass
//--------------
@Entity
@Table(name = "q5991")
class Questionn {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	private String qname;

	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "qid")
	@OrderColumn(name = "type")
	private List<Answerr> answers;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getQname() {
		return qname;
	}

	public void setQname(String qname) {
		this.qname = qname;
	}

	public List<Answerr> getAnswers() {
		return answers;
	}

	public void setAnswers(List<Answerr> answers) {
		this.answers = answers;
	}
}

// Answer class
//--------------
@Entity
@Table(name = "ans5991")
class Answerr {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	private String answername;
	private String postedBy;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getAnswername() {
		return answername;
	}

	public void setAnswername(String answername) {
		this.answername = answername;
	}

	public String getPostedBy() {
		return postedBy;
	}

	public void setPostedBy(String postedBy) {
		this.postedBy = postedBy;
	}
}

// Runner Class
//-------------
public class OneToManyAnnotation {

	public static void main(String[] args) {

		StandardServiceRegistry ssr = new StandardServiceRegistryBuilder().configure("hibernate.cfg.xml").build();
		Metadata meta = new MetadataSources(ssr).getMetadataBuilder().build();

		SessionFactory factory = meta.getSessionFactoryBuilder().build();
		Session session = factory.openSession();

		Transaction tx = session.beginTransaction();

		Answerr ans1 = new Answerr();
		ans1.setAnswername("Java is a programming language");
		ans1.setPostedBy("Ravi Malik");

		Answerr ans2 = new Answerr();
		ans2.setAnswername("Java is a platform");
		ans2.setPostedBy("Sudhir Kumar");

		Answerr ans3 = new Answerr();
		ans3.setAnswername("Servlet is an Interface");
		ans3.setPostedBy("Jai Kumar");

		Answerr ans4 = new Answerr();
		ans4.setAnswername("Servlet is an API");
		ans4.setPostedBy("Arun");

		ArrayList<Answerr> list1 = new ArrayList<Answerr>();
		list1.add(ans1);
		list1.add(ans2);

		ArrayList<Answerr> list2 = new ArrayList<Answerr>();
		list2.add(ans3);
		list2.add(ans4);

		Questionn question1 = new Questionn();
		question1.setQname("What is Java?");
		question1.setAnswers(list1);

		Questionn question2 = new Questionn();
		question2.setQname("What is Servlet?");
		question2.setAnswers(list2);

		session.persist(question1);
		session.persist(question2);

		//To Fetch
		//---------
		TypedQuery<Questionn> query = session.createQuery("from Questionn");
		
		List<Questionn> list = query.getResultList();

		Iterator<Questionn> itr = list.iterator();
		
		while (itr.hasNext()) {
			Questionn q = itr.next();
			System.out.println("Question Name: " + q.getQname());

			// printing answers
			List<Answerr> list3 = q.getAnswers();
			
			Iterator<Answerr> itr2 = list3.iterator();
			
			while (itr2.hasNext()) {
				Answerr a = itr2.next();
				System.out.println(a.getAnswername() + ": " + a.getPostedBy());
			}
		}

		tx.commit();
		session.close();
		System.out.println("One To Many Annotation-success");

	}
}
