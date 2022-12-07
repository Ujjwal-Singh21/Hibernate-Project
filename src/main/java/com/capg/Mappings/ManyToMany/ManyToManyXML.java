package com.capg.Mappings.ManyToMany;

import java.util.ArrayList;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

// Question
class Question1 {
	private int id;
	private String qname;
	private List<Answer1> answers;

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

	public List<Answer1> getAnswers() {
		return answers;
	}

	public void setAnswers(List<Answer1> answers) {
		this.answers = answers;
	}
}

//Answer
class Answer1 {
	
	private int id;
	private String answername;
	private String postedBy;
	private List<Question1> questions;

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

	public List<Question1> getQuestions() {
		return questions;
	}

	public void setQuestions(List<Question1> questions) {
		this.questions = questions;
	}
}

//Main
public class ManyToManyXML {

	public static void main(String[] args) {

		StandardServiceRegistry ssr = new StandardServiceRegistryBuilder().configure("hibernate.cfg.xml").build();
		Metadata meta = new MetadataSources(ssr).getMetadataBuilder().build();
		
		SessionFactory factory = meta.getSessionFactoryBuilder().build();
		Session session = factory.openSession();
		
		Transaction tx = session.beginTransaction();

		Answer1 ans1 = new Answer1();
		ans1.setAnswername("Java is a programming language");
		ans1.setPostedBy("Ravi Malik");

		Answer1 ans2 = new Answer1();
		ans2.setAnswername("Java is a platform");
		ans2.setPostedBy("Sudhir Kumar");

		Question1 q1 = new Question1();
		q1.setQname("What is Java?");
		
		ArrayList<Answer1> l1 = new ArrayList<Answer1>();
		l1.add(ans1);
		l1.add(ans2);
		q1.setAnswers(l1);

		Answer1 ans3 = new Answer1();
		ans3.setAnswername("Servlet is an Interface");
		ans3.setPostedBy("Jai Kumar");

		Answer1 ans4 = new Answer1();
		ans4.setAnswername("Servlet is an API");
		ans4.setPostedBy("Arun");

		Question1 q2 = new Question1();
		q2.setQname("What is Servlet?");
		
		ArrayList<Answer1> l2 = new ArrayList<Answer1>();
		l2.add(ans3);
		l2.add(ans4);
		q2.setAnswers(l2);
		
		session.persist(q1);
		session.persist(q2);

		tx.commit();
		session.close();
		System.out.println("Many To Many XML - success");

	}

}
