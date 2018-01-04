package com.hibernate.dao.impl;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import com.hibernate.dao.EmployeeDAO;
import com.hibernate.entities.Employee;
import com.hibernate.util.HibernateUtil;

public class EmployeeDAOImpl implements EmployeeDAO {

	@Override
	public void createEmployee(Employee employee) {
		SessionFactory sf = HibernateUtil.getSessionFactory();
		Session session = null;
		Transaction transaction = null;
		
		try {
			session = sf.openSession();
			
			transaction = session.beginTransaction();
			session.save(employee);//Persistence State
			transaction.commit();
			
			
			System.out.println("Employee is created...");
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

	@Override
	public Employee getEmployeeById(int employeeId) {
		
		SessionFactory sf = HibernateUtil.getSessionFactory();
		Session session = null;
		Employee employee = null;
		
		try {
			session = sf.openSession();
			
			Object object = session.get(Employee.class, employeeId);
			if(object != null)
				employee=(Employee)object;//Persistence State
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			if(session != null){
				session.close();
			}
		}
		return employee;
	}

	@Override
	public void updateEmailbyId(int employeeId, String newEmail) {

		SessionFactory sf = HibernateUtil.getSessionFactory();
		Session session = null;
		Transaction transaction = null;
		Employee employee= null;
		
		try {
			session = sf.openSession();
			transaction = session.beginTransaction();
			Object object = session.get(Employee.class, employeeId);
			if(object != null){
				 employee=(Employee)object;//Persistence State
				employee.setEmail(newEmail);
			}
			
			session.update(employee);
			
			transaction.commit();
			System.out.println("Email is updated..");
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

	@Override
	public void deleteEmployeeById(int employeeId) {

		SessionFactory sf = HibernateUtil.getSessionFactory();
		Session session = null;
		Transaction transaction = null;
		Employee employee= null;
		
		try {
			session = sf.openSession();
			transaction = session.beginTransaction();
			Object object = session.get(Employee.class, employeeId);
			if(object != null){
				 employee=(Employee)object;
				 session.delete(employee);
			}
			
			transaction.commit();
			System.out.println("Employee is deleted.");
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
