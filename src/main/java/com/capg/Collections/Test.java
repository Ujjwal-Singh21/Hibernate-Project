package com.capg.Collections;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

public class Test {
	
	public static void main(String[] arg) {
		
		Configuration configuration = new Configuration().configure();
		
//		StandardServiceRegistry ssr = new StandardServiceRegistryBuilder().configure("hibernate.cfg.xml").build();  
//        Metadata meta = new MetadataSources(ssr).getMetadataBuilder().build();  

		SessionFactory sessionFactory = configuration.buildSessionFactory();
//		SessionFactory sessionFactory = meta.getSessionFactoryBuilder().build();  
		
		Session session = sessionFactory.openSession();

		Transaction tx = session.beginTransaction();
//		tx.begin(); -> error says transaction is already active

		// Collections Implementations
		//-----------------------------
		List<String> empList = new ArrayList<String>();
		empList.add("asdf");
		empList.add("mnop");
		empList.add("ghij");
		empList.add("pqrst");

		Set<String> empSet = new HashSet<String>();
		empSet.add("A");
		empSet.add("B");
		empSet.add("C");
		empSet.add("D");

		Map<String, String> empMap = new HashMap<String, String>();
		empMap.put("k1", "v1");
		empMap.put("k2", "v2");
		empMap.put("k3", "v3");
		empMap.put("k4", "v4");

		EmployeeBean employee = new EmployeeBean();
		
		employee.setEmpNo(101);
		employee.setEmpName("Ramesh");
		employee.setEmpSal(34555.00);
		employee.setEmpListProj(empList);
		employee.setEmpSetProj(empSet);
		employee.setEmpMapProj(empMap);

		Integer eId = (Integer) session.save(employee);
		
		System.out.println(eId);
		
		tx.commit();
		session.close();
	}
}
