package com.hibernate.client;

import java.util.Date;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import com.hibernate.entities.Department;
import com.hibernate.entities.Employee;
import com.hibernate.util.HibernateUtil;

public class FetchEmployeeInfoTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		SessionFactory sf = HibernateUtil.getSessionFactory();
		Session session = null;
		Scanner scanner = null;
		Transaction transaction = null;

		try {
			session = sf.openSession();
			transaction = session.beginTransaction();

			Department department = new Department();
			department.setName("IT");
			department.setLocation("Aundh");
			department.setAddress("Pune");

			Employee employee1 = getEmployee1();
			Employee employee2 = getEmployee2();
			Set<Employee> employeeSet = new HashSet<Employee>();
			employeeSet.add(employee1);
			employeeSet.add(employee2);

			department.setEmployeesSet(employeeSet);

			session.save(department);

			transaction.commit();
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

		try {

			System.out.println("Please enter Department ID:");
			scanner = new Scanner(System.in);
			int deptId = scanner.nextInt();
			session = sf.openSession();
			Object object = session.get(Department.class, deptId);
			if (object != null) {
				Department dept = (Department) object;
				System.out.println(dept.getId() + "\t" + dept.getAddress()
						+ "\t" + dept.getLocation() + "\t" + dept.getName());

				Set<Employee> employeesSet = dept.getEmployeesSet();
				for (Employee employee : employeesSet) {
					System.out.println(employee.getEmployeeId() + "\t"
							+ employee.getEmployeeName() + "\t"
							+ employee.getEmail() + "\t" + employee.getSalary()
							+ "\t" + employee.getDeptName());
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (session != null) {
				session.close();
			}
		}

	}

	private static Employee getEmployee1() {

		Employee employee = new Employee();
		employee.setDeptName("IT");
		employee.setDoj(new Date());
		employee.setEmail("keyur.555kn@gmail.com");
		employee.setEmployeeId(27);
		employee.setEmployeeName("Keyur");
		employee.setSalary(80000.00d);
		return employee;
	}

	private static Employee getEmployee2() {

		Employee employee = new Employee();
		employee.setDeptName("HR");
		employee.setDoj(new Date());
		employee.setEmail("dave.ashvini@gmail.com");
		employee.setEmployeeId(14);
		employee.setEmployeeName("Ami");
		employee.setSalary(50000.00d);
		return employee;
	}

}
