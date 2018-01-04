package com.hibernate.client;

import java.util.Scanner;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.hibernate.entities.Employee;
import com.hibernate.entities.PanCard;
import com.hibernate.util.HibernateUtil;

public class FetchEmployeeInfoTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		SessionFactory sf = HibernateUtil.getSessionFactory();
		Session session = null; 
		Scanner scanner= null;
		
		try {
			session = sf.openSession();
			System.out.println("Please enter employee ID:");
			scanner = new Scanner(System.in);
			int employeeId = scanner.nextInt();
			
			Object object = session.get(Employee.class, employeeId);
			if(object != null){
				Employee employee=(Employee)object;
				System.out.println(employee.getEmployeeId()+"\t"+employee.getEmployeeName()+"\t"+employee.getEmail()+"\t"+employee.getSalary()+"\t"+employee.getDeptName());
				
				PanCard panCard = employee.getPanCard();
				if(panCard != null){
					System.out.println(panCard.getPancardId()+"\t"+panCard.getPanNo()+"\t"+panCard.getDob());
				}
			}else{
				System.out.println("Employee not found with ID:"+employeeId);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			if(session != null){
				session.close();
			}
		}
	
	

	}

}
