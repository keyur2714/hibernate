package com.hibernate.client;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Example;

import com.hibernate.entities.Employee;
import com.hibernate.util.HibernateUtil;

public class QBEClientTest {

	public static void main(String[] args) {
		Session session = null;
		SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
		try {
			session = sessionFactory.openSession();
			
			Employee exp = new Employee();
			//exp.setEmployeeId(1);//QBE will ignore PK
			exp.setDeptName("IT");
			exp.setEmployeeName("A%");
			
			Example example = Example.create(exp).enableLike();
			Criteria criteria = session.createCriteria(Employee.class);
			criteria.add(example);
			
			List<Employee> empList = criteria.list();
			
			for (Employee employee : empList) {
				System.out.println(employee.getEmployeeId()+"\t"+employee.getEmployeeName()+"\t"+employee.getEmail()+"\t"+employee.getSalary()+"\t"+employee.getDeptName());
			}
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			if(session != null)
				session.close();
		}
	}
}
