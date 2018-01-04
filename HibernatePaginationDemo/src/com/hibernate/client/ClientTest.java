package com.hibernate.client;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import com.hibernate.entities.Employee;
import com.hibernate.util.HibernateUtil;

public class ClientTest {

	public static void main(String[] args) {

		SessionFactory sf = HibernateUtil.getSessionFactory();
		Session session = null;
		Transaction transaction = null;

		try {
			session = sf.openSession();

			transaction = session.beginTransaction();
			for (int i = 0; i < 101; i++) {
				Employee employee = new Employee();
				employee.setBonus(new BigDecimal(100.262));
				employee.setEmployeeName("Keyur_" + i);
				employee.setDeptName("IT_" + i);
				employee.setDoj(new Date());
				employee.setSalary(80000.2727d + i);
				employee.setEmail("keyur_" + i + "@gmail.com");
				session.save(employee);
			}

			transaction.commit();
			System.out.println("Employee is created...");
		} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}
			e.printStackTrace();
		} finally {
			if (session != null) {
				session.close();
			}
		}
		
		paginationUsingQuery();
		paginationUsingCriteria();
	}

	private static void paginationUsingQuery() {
		Session session = null;
		SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
		try {
			session = sessionFactory.openSession();

			Query query = session.createQuery("FROM Employee");
			query.setMaxResults(10);

			for (int i = 0; i < 20; i++) {
				query.setFirstResult(10 * i);

				List<Employee> empList = query.list();
				if (empList.size() == 0)
					break;
				System.out.println("::::::::::::::::::::::::::::::::::::::");
				for (Employee employee : empList) {
					System.out.println(employee.getEmployeeId() + "\t"
							+ employee.getEmployeeName() + "\t"
							+ employee.getEmail() + "\t" + employee.getSalary()
							+ "\t" + employee.getDeptName());
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (session != null)
				session.close();
		}
	}

	private static void paginationUsingCriteria() {
		Session session = null;
		SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
		try {
			session = sessionFactory.openSession();
			Criteria criteria = session.createCriteria(Employee.class);

			criteria.setMaxResults(10);

			for (int i = 0; i < 20; i++) {
				criteria.setFirstResult(10 * i);

				List<Employee> empList = criteria.list();
				if (empList.size() == 0)
					break;
				System.out.println("::::::::::::::::::::::::::::::::::::::");
				for (Employee employee : empList) {
					System.out.println(employee.getEmployeeId() + "\t"
							+ employee.getEmployeeName() + "\t"
							+ employee.getEmail() + "\t" + employee.getSalary()
							+ "\t" + employee.getDeptName());
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (session != null)
				session.close();
		}
	}

}
