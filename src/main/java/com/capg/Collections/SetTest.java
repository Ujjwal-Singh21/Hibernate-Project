package com.capg.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import javax.persistence.TypedQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

public class SetTest {

	public static void main(String[] args) {

		StandardServiceRegistry ssr = new StandardServiceRegistryBuilder().configure("hibernate.cfg.xml").build();
		Metadata meta = new MetadataSources(ssr).getMetadataBuilder().build();

		SessionFactory factory = meta.getSessionFactoryBuilder().build();
		Session session = factory.openSession();

		Transaction tx = session.beginTransaction();

		HashSet<String> set1 = new HashSet<String>();
		set1.add("Java is a programming language");
		set1.add("Java is a platform");

		HashSet<String> set2 = new HashSet<String>();
		set2.add("Servlet is an Interface");
		set2.add("Servlet is an API");

		SetQuestion question1 = new SetQuestion();
		question1.setQname("What is Java?");
		question1.setAnswers(set1);

		SetQuestion question2 = new SetQuestion();
		question2.setQname("What is Servlet?");
		question2.setAnswers(set2);

		session.persist(question1);
		session.persist(question2);

		// How to fetch the data of Set
		// Here, we have used HQL to fetch all the records of Question class including answers. 
		// In such case, it fetches the data from two tables that are functional dependent.
		//--------------------------------------------------------------------------------------
		TypedQuery query = session.createQuery("from SetQuestion");
		List<SetQuestion> list = query.getResultList();

		Iterator<SetQuestion> itr = list.iterator();
		
		while (itr.hasNext()) {
			SetQuestion q = itr.next();
			System.out.println("Question Name: " + q.getQname());

			// printing answers
			Set<String> set = q.getAnswers();
			Iterator<String> itr2 = set.iterator();
			
			while (itr2.hasNext()) {
				System.out.println(itr2.next());
			}

		}

		tx.commit();
		session.close();
		System.out.println("set-success");
	}
}
