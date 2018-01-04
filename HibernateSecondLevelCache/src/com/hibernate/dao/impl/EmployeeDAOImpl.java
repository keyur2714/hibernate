package com.hibernate.dao.impl;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.hibernate.dao.EmployeeDAO;
import com.hibernate.entities.Employee;
import com.hibernate.util.HibernateUtil;

public class EmployeeDAOImpl implements EmployeeDAO {


	@Override
	public void getEmployeeById(int employeeId) {
		
		SessionFactory sf = HibernateUtil.getSessionFactory();
		Session session1 = null;
		Session session2 = null;
		Employee employee1 = null;
		Employee employee2 = null;
		
		try {
			session1 = sf.openSession();
			
			Object object1 = session1.get(Employee.class, employeeId);
			if(object1 != null)
				employee1=(Employee)object1;
			if(employee1 != null){
				System.out.println(employee1.getEmployeeId()+"\t"+employee1.getEmployeeName()+"\t"+employee1.getEmail()+"\t"+employee1.getSalary()+"\t"+employee1.getDeptName());
			}else{
				System.out.println("Employee is not found..");
			}
			
			session1.close();
			
			session2 = sf.openSession();
			Object object2 = session2.get(Employee.class, employeeId);
			if(object2 != null)
				employee2=(Employee)object2;
			if(employee1 != null){
				System.out.println(employee2.getEmployeeId()+"\t"+employee2.getEmployeeName()+"\t"+employee2.getEmail()+"\t"+employee2.getSalary()+"\t"+employee2.getDeptName());
			}else{
				System.out.println("Employee is not found..");
			}

			session2.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
