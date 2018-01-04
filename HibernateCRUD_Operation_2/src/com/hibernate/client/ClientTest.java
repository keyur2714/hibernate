package com.hibernate.client;

import java.math.BigDecimal;
import java.util.Date;

import com.hibernate.dao.EmployeeDAO;
import com.hibernate.dao.impl.EmployeeDAOImpl;
import com.hibernate.entities.Employee;

public class ClientTest {

	public static void main(String[] args) {

		EmployeeDAO employeeDAO = new EmployeeDAOImpl();

		createEmployee(employeeDAO);

		System.out.println("Employee Details Before Update Email Address. ");
		getEmployeeById(employeeDAO);

		employeeDAO.updateEmailbyId(1, "dave.ashvini@gmail.com");

		System.out.println("Employee Details After Update Email Address. ");
		getEmployeeById(employeeDAO);

		employeeDAO.deleteEmployeeById(1);
		System.out.println("Employee Details After Delete Employee. ");
		getEmployeeById(employeeDAO);

	}

	private static void getEmployeeById(EmployeeDAO employeeDAO) {

		Employee employee = employeeDAO.getEmployeeById(1);
		if (employee != null) {
			System.out.println(employee.getEmployeeId() + "\t"
					+ employee.getEmployeeName() + "\t" + employee.getEmail()
					+ "\t" + employee.getSalary() + "\t"
					+ employee.getDeptName());
		} else {
			System.out.println("Employee is not found..");
		}
	}

	private static void createEmployee(EmployeeDAO employeeDAO) {
		Employee employee = getEmployee();
		employeeDAO.createEmployee(employee);
	}

	private static Employee getEmployee() {

		Employee employee = new Employee();
		employee.setDeptName("IT");
		employee.setDoj(new Date());
		employee.setEmail("keyur.555kn@gmail.com");
		employee.setEmployeeName("Keyur");
		employee.setSalary(70000.2727d);

		employee.setBonus(new BigDecimal("727.27"));
		return employee;
	}

}
