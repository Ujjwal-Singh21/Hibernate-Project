package com.capg.Collections;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.persistence.TypedQuery;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

public class MapTest {

	public static void main(String[] args) {

		StandardServiceRegistry ssr = new StandardServiceRegistryBuilder().configure("hibernate.cfg.xml").build();
		Metadata meta = new MetadataSources(ssr).getMetadataBuilder().build();

		SessionFactory factory = meta.getSessionFactoryBuilder().build();
		Session session = factory.openSession();

		Transaction t = session.beginTransaction();

		HashMap<String, String> map1 = new HashMap<String, String>();
		map1.put("Java is a programming language", "John Milton");
		map1.put("Java is a platform", "Ashok Kumar");

		HashMap<String, String> map2 = new HashMap<String, String>();
		map2.put("Servlet technology is a server side programming", "John Milton");
		map2.put("Servlet is an Interface", "Ashok Kumar");
		map2.put("Servlet is a package", "Rahul Kumar");

		MapQuestion question1 = new MapQuestion("What is Java?", "Alok", map1);
		MapQuestion question2 = new MapQuestion("What is Servlet?", "Jai Dixit", map2);

		session.persist(question1);
		session.persist(question2);

		//Fetch
		TypedQuery<MapQuestion> query = session.createQuery("from MapQuestion ");
		List<MapQuestion> list = query.getResultList();

		Iterator<MapQuestion> iterator = list.iterator();
		while (iterator.hasNext()) {
			MapQuestion question = iterator.next();
			System.out.println("question id:" + question.getId());
			System.out.println("question name:" + question.getName());
			System.out.println("question posted by:" + question.getUsername());
			System.out.println("answers.....");
			
			Map<String, String> map = question.getAnswers();
			
			Set<Map.Entry<String, String>> set = map.entrySet();

			Iterator<Map.Entry<String, String>> i = set.iterator();
			
			while (i.hasNext()) {
				Map.Entry<String, String> entry = (Map.Entry<String, String>) i.next();
				System.out.println("answer name:" + entry.getKey());
				System.out.println("answer posted by:" + entry.getValue());
			}
		}

		t.commit();
		session.close();
		System.out.println("map-successfully stored");
	}
}
