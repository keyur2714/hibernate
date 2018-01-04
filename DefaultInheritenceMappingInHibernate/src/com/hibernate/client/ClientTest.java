package com.hibernate.client;

import java.math.BigDecimal;
import java.util.List;

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
			person.setName("Keyur Thakor");
			person.setGender("Male");
			
			Employee employee = new Employee();
			employee.setBonus(new BigDecimal("777.777"));
			employee.setDeptName("IT");
			employee.setDoj(HibernateUtil.getDoj("18/11/2013"));
			employee.setEmail("keyur.555kn@gmail.com");
			employee.setName("Keyur");
			employee.setSalary(80000.2772);
			
			employee.setGender("Male");
			
			Student student = new Student();
			student.setName("Ami");
			student.setGender("Female");
			student.setFee(20000.00f);
			student.setSchoolName("DIV");
			student.setSectionName("1oth Std");
			
			session.save(person);
			session.save(student);
			session.save(employee);
			
			System.out.println("Person Data Saved Successfully in (person_table)");
			
			transaction.commit();
						
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
