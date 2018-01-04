package com.hibernate.client;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;

import com.hibernate.entities.Employee;
import com.hibernate.util.HibernateUtil;

public class FetchEmployeeInfoTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		SessionFactory sf = HibernateUtil.getSessionFactory();
		Session session = null; 
		
		try {
			session = sf.openSession();
			
			DetachedCriteria detachedCriteria=DetachedCriteria.forClass(Employee.class);
			
			detachedCriteria.addOrder(Order.asc("salary"));
			detachedCriteria.addOrder(Order.asc("employeeName"));
			
			List<Employee> empList = detachedCriteria.getExecutableCriteria(session).list();
			
			for (Employee employee : empList) {
				System.out.println(employee.getEmployeeId()+"\t"+employee.getEmployeeName()+"\t"+employee.getEmail()+"\t"+employee.getSalary()+"\t"+employee.getDeptName());
			}
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			if(session != null){
				session.close();
			}
		}
	
	

	}

}
