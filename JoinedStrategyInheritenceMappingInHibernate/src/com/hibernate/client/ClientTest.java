package com.hibernate.client;

import java.math.BigDecimal;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import com.hibernate.entities.Employee;
import com.hibernate.entities.Person;
import com.hibernate.entities.Student;
import com.hibernate.util.HibernateUtil;


public class ClientTest {

	public static void main(String[] args) {


		SessionFactory sf = HibernateUtil.getSessionFactory();
		Session session = null;
		Transaction transaction = null;
		
		try {
			session = sf.openSession();
			transaction =session.beginTransaction();
			
			Person person = new Person();
			person.setName("Keyur");
			person.setGender("Male");
			
			Employee employee = new Employee();
			employee.setBonus(new BigDecimal("777.271"));
			employee.setDeptName("IT");
			employee.setDoj(HibernateUtil.getDoj("18/11/2013"));
			employee.setEmail("dave.ashvini@gmail.com");
			employee.setName("Ami");
			employee.setSalary(70000.1414);
			
			employee.setGender("Female");
			
			Student student = new Student();
			student.setName("Denish");
			student.setGender("Male");
			student.setFee(20000.00f);
			student.setSchoolName("NJ");
			student.setSectionName("12th Std");
			
			session.save(person);
			session.save(student);
			session.save(employee);
			
			transaction.commit();
			
			System.out.println("Data Saved Successfully...!");
			
		} catch (Exception e) {
			if(transaction != null){
				transaction.rollback();
			}
			e.printStackTrace();
		}finally{
			if(session !=null){
				session.close();
			}
		}
	

	}


}
