package com.hibernate.client;

import java.math.BigDecimal;
import java.util.Date;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import com.hibernate.entities.Employee;
import com.hibernate.entities.PanCard;
import com.hibernate.util.HibernateUtil;


public class ClientTest {

	public static void main(String[] args) {


		SessionFactory sf = HibernateUtil.getSessionFactory();
		Session session = null;
		Transaction transaction = null;
		
		try {
			session = sf.openSession();
			
			transaction = session.beginTransaction();
			
			Employee employee = new Employee();
			employee.setDeptName("IT");
			employee.setDoj(new Date());
			employee.setEmail("dave.ashvini@gmail.com");
			employee.setEmployeeName("Ami");
			employee.setSalary(60000.2714d);
			
			employee.setBonus(new BigDecimal("700.2714"));
			
			
			PanCard panCard = new PanCard();
			panCard.setPanNo("AMUPT4012A");
			
			panCard.setDob(HibernateUtil.getDoj("24/06/2009"));
			
			employee.setPanCard(panCard);
			panCard.setEmployee(employee);
			
			session.save(employee);
			//session.save(panCard);
			
			transaction.commit();
		} catch (Exception e) {
			if(transaction != null){
				transaction.rollback();
			}
			e.printStackTrace();
		}finally{
			if(session != null){
				session.close();
			}
		}
	
	}


}
