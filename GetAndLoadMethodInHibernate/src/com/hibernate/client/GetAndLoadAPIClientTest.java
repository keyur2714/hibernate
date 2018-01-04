package com.hibernate.client;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.hibernate.entities.Employee;
import com.hibernate.util.HibernateUtil;

public class GetAndLoadAPIClientTest {

	public static void main(String[] args) {

		SessionFactory sf = HibernateUtil.getSessionFactory();

		getEmpDetailsByIdUsingGetAPI(sf);//Always Hit Database and return actual database row.
		getEmpDetailsByIdUsingLoadAPI(sf);//Not Hit the database and return proxy object if no matiching row found it returns ObjectNotFoundException
	}

	private static void getEmpDetailsByIdUsingLoadAPI(SessionFactory sf) {

		Session session = null;
		int empId = 1;

		try {
			session = sf.openSession();

			Object object = session.load(Employee.class, empId);
			if (object != null) {
				Employee employee = (Employee) object;

				System.out.println(employee.getEmployeeId() + "\t"
						+ employee.getEmployeeName() + "\t"
						+ employee.getEmail() + "\t" + employee.getSalary()
						+ "\t" + employee.getDeptName());
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (session != null)
				session.close();
		}
	}

	private static void getEmpDetailsByIdUsingGetAPI(SessionFactory sf) {

		Session session = null;
		int empId = 1;
		try {
			session = sf.openSession();

			Object object = session.get(Employee.class, empId);
			if (object != null) {
				Employee employee = (Employee) object;
				System.out.println(employee.getEmployeeId() + "\t"
						+ employee.getEmployeeName() + "\t"
						+ employee.getEmail() + "\t" + employee.getSalary()
						+ "\t" + employee.getDeptName());
			} else {
				System.out.println("Employee donesn't exit with ID:" + empId);
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (session != null)
				session.close();
		}
	}

}
