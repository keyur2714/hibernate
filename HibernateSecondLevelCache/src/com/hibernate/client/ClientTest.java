package com.hibernate.client;

import com.hibernate.dao.EmployeeDAO;
import com.hibernate.dao.impl.EmployeeDAOImpl;

public class ClientTest {

	public static void main(String[] args) {

		EmployeeDAO employeeDAO = new EmployeeDAOImpl();
		employeeDAO.getEmployeeById(1);

	}

}
