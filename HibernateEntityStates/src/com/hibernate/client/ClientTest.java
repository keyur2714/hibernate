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
		System.out.println("Before Update");
		getEmployeeById(employeeDAO);
		
		employeeDAO.updateEmailbyId(1, "dave.ashvini@gmail.com");
		System.out.println("After Update");
		getEmployeeById(employeeDAO);
		
		
		employeeDAO.deleteEmployeeById(1);
		System.out.println("After Delete");
		employeeDAO.deleteEmployeeById(1);

	}

	private static void getEmployeeById(EmployeeDAO employeeDAO) {
		
		Employee employee = employeeDAO.getEmployeeById(1);
		if(employee != null){
			System.out.println(employee.getEmployeeId()+"\t"+employee.getEmployeeName()+"\t"+employee.getEmail()+"\t"+employee.getSalary()+"\t"+employee.getDeptName());
		}else{
			System.out.println("Employee is not found..");
		}
	}

	private static void createEmployee(EmployeeDAO employeeDAO) {
		Employee employee=	getEmployee();//Transient State
		employeeDAO.createEmployee(employee);
	}

	private static Employee getEmployee() {
		
		Employee employee = new Employee();
		employee.setDeptName("IT");
		employee.setDoj(new Date());
		employee.setEmail("keyur.555kn@gmail.com");
		employee.setEmployeeName("Keyur");
		employee.setSalary(80000.7672d);
		
		employee.setBonus(new BigDecimal("777.2727"));
		return employee;
	}

}
