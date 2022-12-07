package com.capg.Mappings.OneToMany;
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

//Question Class
class Question {

	private int id;
	private String qname;
	private List<Answer> answers;

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

	public List<Answer> getAnswers() {
		return answers;
	}

	public void setAnswers(List<Answer> answers) {
		this.answers = answers;
	}
}

//Answer Class
class Answer {

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

// Runner-Class
public class OneToManyXML {

	public static void main(String[] args) {

		StandardServiceRegistry ssr = new StandardServiceRegistryBuilder().configure("hibernate.cfg.xml").build();
		Metadata meta = new MetadataSources(ssr).getMetadataBuilder().build();

		SessionFactory factory = meta.getSessionFactoryBuilder().build();
		Session session = factory.openSession();

		Transaction tx = session.beginTransaction();

		Answer ans1 = new Answer();
		ans1.setAnswername("Java is a programming language");
		ans1.setPostedBy("Ravi Malik");

		Answer ans2 = new Answer();
		ans2.setAnswername("Java is a platform");
		ans2.setPostedBy("Sudhir Kumar");

		Answer ans3 = new Answer();
		ans3.setAnswername("Servlet is an Interface");
		ans3.setPostedBy("Jai Kumar");

		Answer ans4 = new Answer();
		ans4.setAnswername("Servlet is an API");
		ans4.setPostedBy("Arun");

		ArrayList<Answer> list1 = new ArrayList<Answer>();
		list1.add(ans1);
		list1.add(ans2);

		ArrayList<Answer> list2 = new ArrayList<Answer>();
		list2.add(ans3);
		list2.add(ans4);

		Question question1 = new Question();
		question1.setQname("What is Java? ");
		question1.setAnswers(list1);

		Question question2 = new Question();
		question2.setQname("What is Servlet? ");
		question2.setAnswers(list2);

		session.persist(question1);
		session.persist(question2);

		//Fetch
		TypedQuery<Question> query = session.createQuery("from Question");
		List<Question> list = query.getResultList();

		Iterator<Question> itr = list.iterator();
		
		while (itr.hasNext()) {
			
			Question q = itr.next();
			System.out.println("Question Name: " + q.getQname());

			// printing answers
			List<Answer> list3 = q.getAnswers();
			Iterator<Answer> itr2 = list3.iterator();
			
			while (itr2.hasNext()) {
				Answer a = itr2.next();
				System.out.println(a.getAnswername() + ": " + a.getPostedBy());
			}
		}
		session.close();
		System.out.println("OneToManyXML-success");
	}
}
