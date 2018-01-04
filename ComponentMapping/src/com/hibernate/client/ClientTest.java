package com.hibernate.client;

import java.math.BigDecimal;
import java.text.ParseException;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import com.hibernate.entities.Address;
import com.hibernate.entities.Employee;
import com.hibernate.util.HibernateUtil;

public class ClientTest {

	public static void main(String[] args) {

		SessionFactory sf = HibernateUtil.getSessionFactory();
		Session session = null;
		Transaction transaction = null;
		
		try {
			session = sf.openSession();
			transaction =session.beginTransaction();
			
			Employee employee = getEmployeeDetails();
			
			session.save(employee);
			transaction.commit();
			System.out.println("Employee Data Saved Successfully...");
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

	private static Employee getEmployeeDetails()
			throws ParseException {
		Employee employee = new Employee();
		Address address = new Address();
		address.setCity("Pune");
		address.setAddressLine1("Kunal Icon Road");
		address.setAddressLine2("Opposite Rajveer Palace");
		address.setStreet("Alcove Society,Pimple Saudagar");
		address.setZipCode(411027L);
		
		
		employee.setAddress(address);
		employee.setBonus(new BigDecimal("777.232"));
		employee.setDeptName("IT");
		employee.setDoj(HibernateUtil.getDoj("18/11/2013"));
		employee.setEmail("keyur.555kn@gmail.com");
		employee.setEmployeeName("Keyur");
		employee.setSalary(80400.20);
		return employee;
	}

}
