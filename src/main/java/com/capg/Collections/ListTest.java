package com.capg.Collections;
import java.util.ArrayList;
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

public class ListTest {

	public static void main(String[] args) {

		StandardServiceRegistry ssr = new StandardServiceRegistryBuilder().configure("hibernate.cfg.xml").build();
		Metadata meta = new MetadataSources(ssr).getMetadataBuilder().build();

		SessionFactory factory = meta.getSessionFactoryBuilder().build();
		Session session = factory.openSession();

		Transaction tx = session.beginTransaction();

		ArrayList<String> list1 = new ArrayList<String>();
		list1.add("Java is a programming language");
		list1.add("Java is a platform");

		ArrayList<String> list2 = new ArrayList<String>();
		list2.add("Servlet is an Interface");
		list2.add("Servlet is an API");

		ListQuestion question1 = new ListQuestion();
		question1.setQname("What is Java?");
		question1.setAnswers(list1);

		ListQuestion question2 = new ListQuestion();
		question2.setQname("What is Servlet?");
		question2.setAnswers(list2);

		session.persist(question1);
		session.persist(question2);

		// Here, we have used HQL to fetch all the records of Question class including answers. 
		// In such case, it fetches the data from two tables that are functional dependent.
		//-------------------------------------------------------------------------------------
		TypedQuery<ListQuestion> query = session.createQuery("from ListQuestion");
		List<ListQuestion> list = query.getResultList();

		Iterator<ListQuestion> itr = list.iterator();
		
		while (itr.hasNext()) {
			ListQuestion q = itr.next();
			System.out.println("Question Name: " + q.getQname());

			// printing answers
			List<String> answerList = q.getAnswers();
			
			Iterator<String> itr2 = answerList.iterator();
			
			while (itr2.hasNext()) {
				System.out.println(itr2.next());
			}
		}
		
		tx.commit();
		session.close();
		System.out.println("list-success");
	}
}
