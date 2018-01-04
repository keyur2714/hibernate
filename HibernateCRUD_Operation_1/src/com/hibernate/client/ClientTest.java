package com.hibernate.client;

import java.util.Date;

import com.hibernate.dao.EmployeeDAO;
import com.hibernate.dao.impl.EmployeeDAOImpl;
import com.hibernate.entities.Employee;

public class ClientTest {

	public static void main(String[] args) {

		EmployeeDAO employeeDAO = new EmployeeDAOImpl();
		int employeeId = 27;

		createEmployee(employeeDAO);
		System.out.println("Before Update Email Address...");
		getEmployeeById(employeeDAO,employeeId);
		employeeDAO.updateEmailbyId(27, "dave.ashvini@gmail.com");
		System.out.println("After Update Email Address...");		
		getEmployeeById(employeeDAO,employeeId);
		employeeDAO.deleteEmployeeById(27);
		System.out.println("After Deleting Employee ...");		
		getEmployeeById(employeeDAO,employeeId);
	}

	private static void getEmployeeById(EmployeeDAO employeeDAO,int employeeId) {
		
		Employee employee = employeeDAO.getEmployeeById(employeeId);
		if(employee != null){
			System.out.println(employee.getEmployeeId()+"\t"+employee.getEmployeeName()+"\t"+employee.getEmail()+"\t"+employee.getSalary()+"\t"+employee.getDeptName());
		}else{
			System.out.println("Employee is not found..");
		}
	}

	private static void createEmployee(EmployeeDAO employeeDAO) {
		Employee employee=	getEmployee();
		employeeDAO.createEmployee(employee);
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
