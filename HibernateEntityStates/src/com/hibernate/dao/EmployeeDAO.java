package com.hibernate.dao;

import com.hibernate.entities.Employee;

public interface EmployeeDAO {

	public abstract  void createEmployee(Employee employee);
	public abstract Employee getEmployeeById(int employeeId);
	public abstract void updateEmailbyId(int employeeId,String newEmail);
	public abstract void deleteEmployeeById(int employeeId);
}
