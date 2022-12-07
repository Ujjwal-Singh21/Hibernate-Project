package com.capg.Mappings.ManyToMany;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

// Question
@Entity
@Table(name = "ques1123")
class Question2 {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	private String qname;

	@ManyToMany(targetEntity = Answer2.class, cascade = { CascadeType.ALL })
	@JoinTable(name = "q_ans1123",
	           joinColumns = { @JoinColumn(name = "q_id") }, 
	           inverseJoinColumns = {@JoinColumn(name = "ans_id") }
	          )
	private List<Answer2> answers;

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

	public List<Answer2> getAnswers() {
		return answers;
	}

	public void setAnswers(List<Answer2> answers) {
		this.answers = answers;
	}
}

// Answer
@Entity
@Table(name = "ans1123")
class Answer2 {

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

// Main Class
public class ManyToManyAnnotation {

	public static void main(String[] args) {

		StandardServiceRegistry ssr = new StandardServiceRegistryBuilder().configure("hibernate.cfg.xml").build();
		Metadata meta = new MetadataSources(ssr).getMetadataBuilder().build();
		
		SessionFactory factory = meta.getSessionFactoryBuilder().build();
		Session session = factory.openSession();
		
		Transaction tx = session.beginTransaction();

		Answer2 an1 = new Answer2();
		an1.setAnswername("Java is a programming language");
		an1.setPostedBy("Ravi Malik");

		Answer2 an2 = new Answer2();
		an2.setAnswername("Java is a platform");
		an2.setPostedBy("Sudhir Kumar");

		Question2 q1 = new Question2();
		q1.setQname("What is Java?");
		
		ArrayList<Answer2> l1 = new ArrayList<Answer2>();
		l1.add(an1);
		l1.add(an2);
		q1.setAnswers(l1);

		Answer2 ans3 = new Answer2();
		ans3.setAnswername("Servlet is an Interface");
		ans3.setPostedBy("Jai Kumar");

		Answer2 ans4 = new Answer2();
		ans4.setAnswername("Servlet is an API");
		ans4.setPostedBy("Arun");

		Question2 q2 = new Question2();
		q2.setQname("What is Servlet?");
		
		ArrayList<Answer2> l2 = new ArrayList<Answer2>();
		l2.add(ans3);
		l2.add(ans4);
		q2.setAnswers(l2);

		session.persist(q1);
		session.persist(q2);

		tx.commit();
		session.close();
		System.out.println("Mant To Many Annotation - success");

	}
}
