package com.hibernate.client;

import java.util.Date;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import com.hibernate.entities.Employee;
import com.hibernate.util.HibernateUtil;

public class ClientTest {

	public static void main(String[] args) {

		SessionFactory sf = HibernateUtil.getSessionFactory();
		Employee employee = getEmployee();
		saveEmployee(employee);
		System.out.println("================1===================");
		getAllEmployeesUsingHQL(sf);
		updateEmployeeEmail(sf,27, "dave.ashvini@gmail.com");
		System.out.println("=================2==================");
		getAllEmployeesUsingHQL(sf);
		System.out.println("==================3=================");
		getEmployeeByIdUsingHQL1(sf);
		System.out.println("===================4================");
		getEmployeeByIdUsingHQL2(sf);
		System.out.println("====================5===============");
		getEmployeeByIdUsingHQL3(sf);
		System.out.println("=====================6==============");
		getEmployeeNames(sf);
		System.out.println("======================7=============");
		getEmployeeIdAndName(sf);
		System.out.println("=======================8============");

	}

	private static void getEmployeeNames(SessionFactory sf) {

		Session session = null;
		String HQL = "SELECT employeeName FROM Employee";
		try {
			session = sf.openSession();

			Query query = session.createQuery(HQL);

			List<String> list = query.list();
			for (String name : list) {
				System.out.println(name);
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (session != null)
				session.close();
		}

	}

	private static void getEmployeeIdAndName(SessionFactory sf) {

		Session session = null;
		String HQL = "SELECT employeeId,employeeName FROM Employee";
		try {
			session = sf.openSession();

			Query query = session.createQuery(HQL);

			List<Object[]> list = query.list();
			for (Object[] objects : list) {
				int employeeId = (Integer) objects[0];
				String employeeName = (String) objects[1];
				System.out.println(employeeId + "\t" + employeeName);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (session != null)
				session.close();
		}

	}

	private static void saveEmployee(Employee employee) {
		SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
		Transaction transaction = null;
		Session session = null;

		try {
			session = sessionFactory.openSession();

			transaction = session.beginTransaction();

			session.save(employee);
			System.out.println("Employee is Inserted Successfully");

			transaction.commit();

		} catch (Exception e) {
			e.printStackTrace();			
		} finally {
			if (session != null)
				session.close();
		}

	}

	private static void updateEmployeeEmail(SessionFactory sf,int employeeId, String newEmail) {

		Transaction transaction = null;
		Session session = null;

		String HQL = "UPDATE Employee set email=:newEmail WHERE employeeId=:empId";

		try {
			session = sf.openSession();

			transaction = session.beginTransaction();

			Query query = session.createQuery(HQL);

			query.setParameter("newEmail", newEmail);
			query.setParameter("empId", employeeId);

			int executeUpdate = query.executeUpdate();
			if (executeUpdate > 0) {
				System.out.println("Email is updated");
			}

			transaction.commit();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (session != null)
				session.close();
		}
	}

	private static void getEmployeeByIdUsingHQL3(SessionFactory sf) {

		int empId = 27;
		String newEmail = "dave.ashvini@gmail.com";
		Session session = null;
		String HQL = "FROM Employee WHERE employeeId=:empId AND email=:newEmail ";
		try {
			session = sf.openSession();
			Query query = session.createQuery(HQL);

			query.setParameter("newEmail", newEmail);
			query.setParameter("empId", empId);

			Object object = query.uniqueResult();

			if (object != null) {
				Employee employee = (Employee) object;
				System.out
						.println(employee.getEmployeeId() + "\t"
								+ employee.getEmployeeName() + "\t"
								+ employee.getSalary() + "\t"
								+ employee.getDeptName() + "\t"
								+ employee.getDoj() + "\t"
								+ employee.getEmail());
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (session != null)
				session.close();
		}

	}

	private static void getEmployeeByIdUsingHQL2(SessionFactory sf) {

		int empId = 27;
		String newEmail = "dave.ashvini@gmail.com";
		Session session = null;
		String HQL = "FROM Employee WHERE employeeId=? AND email=? ";
		try {
			session = sf.openSession();
			Query query = session.createQuery(HQL);

			query.setParameter(0, empId);
			query.setParameter(1, newEmail);

			Object object = query.uniqueResult();

			if (object != null) {
				Employee employee = (Employee) object;
				System.out
						.println(employee.getEmployeeId() + "\t"
								+ employee.getEmployeeName() + "\t"
								+ employee.getSalary() + "\t"
								+ employee.getDeptName() + "\t"
								+ employee.getDoj() + "\t"
								+ employee.getEmail());
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (session != null)
				session.close();
		}

	}

	private static void getEmployeeByIdUsingHQL1(SessionFactory sf) {

		int empId = 27;
		Session session = null;
		String HQL = "FROM Employee WHERE employeeId=?";
		try {
			session = sf.openSession();
			Query query = session.createQuery(HQL);

			query.setParameter(0, empId);

			Object object = query.uniqueResult();

			if (object != null) {
				Employee employee = (Employee) object;
				System.out
						.println(employee.getEmployeeId() + "\t"
								+ employee.getEmployeeName() + "\t"
								+ employee.getSalary() + "\t"
								+ employee.getDeptName() + "\t"
								+ employee.getDoj() + "\t"
								+ employee.getEmail());
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (session != null)
				session.close();
		}

	}

	private static void getAllEmployeesUsingHQL(SessionFactory sf) {
		Session session = null;

		String HQL = "FROM Employee";
		try {
			session = sf.openSession();

			Query query = session.createQuery(HQL);

			List<Employee> list = query.list();
			for (Employee employee : list) {
				System.out
						.println(employee.getEmployeeId() + "\t"
								+ employee.getEmployeeName() + "\t"
								+ employee.getSalary() + "\t"
								+ employee.getDeptName() + "\t"
								+ employee.getDoj() + "\t"
								+ employee.getEmail());
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (session != null)
				session.close();
		}
	}

	private static Employee getEmployee() {

		Employee employee = new Employee();
		employee.setDeptName("IT");
		employee.setDoj(new Date());
		employee.setEmail("keyur.555kn@gmail.com");
		employee.setEmployeeId(27);
		employee.setEmployeeName("Keyur");
		employee.setSalary(80000.00d);
		return employee;
	}
}
