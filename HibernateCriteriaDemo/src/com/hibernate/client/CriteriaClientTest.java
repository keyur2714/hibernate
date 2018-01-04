package com.hibernate.client;

import java.math.BigDecimal;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.ProjectionList;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.transform.Transformers;

import com.hibernate.entities.Employee;
import com.hibernate.entities.PanCard;
import com.hibernate.util.HibernateUtil;
import com.hibernate.vo.EmployeeVO;

public class CriteriaClientTest {

	public static void main(String[] args) {

		SessionFactory sf = HibernateUtil.getSessionFactory();
		
		getAllEmployeesDetails(sf);
		getEmployeeDetailsById(sf);
		getAllEmployeesDetailsAndSortBasedOnSalary(sf);
		selectIdAndEmpName(sf);
		getAllEmployeesAvgAndTotalSalary(sf);
		EmployeeVO employeeVO = getAllEmployeesDetailsAndMapToVOs(sf);
		if(employeeVO != null){
			System.out.println("Average Salary :"+employeeVO.getAvgSalary());
			System.out.println("Total Salary :"+employeeVO.getTotalSal());
			System.out.println("Total Bonus :"+employeeVO.getTotalBonus());
		}
	}

	private static EmployeeVO getAllEmployeesDetailsAndMapToVOs(SessionFactory sf) {

		Session session = null;
		EmployeeVO employeeVO = null;
		
		try {
			session = sf.openSession();
			
			Criteria criteria = session.createCriteria(Employee.class);
			
			criteria.setProjection(Projections.projectionList().add(Projections.avg("salary"),"avgSalary").add(Projections.sum("salary"),"totalSal").add(Projections.sum("bonus"),"totalBonus"));
			
			criteria.setResultTransformer(Transformers.aliasToBean(EmployeeVO.class));
			
			Object uniqueResult = criteria.uniqueResult();
			employeeVO=(EmployeeVO)uniqueResult;
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			if(session != null)
				session.close();
		}
		return employeeVO;
	}
	private static void getAllEmployeesAvgAndTotalSalary(SessionFactory sf) {

		Session session = null;
		
		try {
			session = sf.openSession();
			
			Criteria criteria = session.createCriteria(Employee.class);
			
			criteria.setProjection(Projections.projectionList().add(Projections.avg("salary")).add(Projections.sum("salary")).add(Projections.sum("bonus")));
			
			List<Object[]> list = criteria.list();
			for (Object[] objects : list) {
				Double avgSal =(Double)objects[0];
				Double totalSal =(Double)objects[1];
				BigDecimal toatalBonus=(BigDecimal)objects[2];
				System.out.println("Average Salary :"+avgSal);
				System.out.println("Total Salary :"+totalSal);
				System.out.println("Total Bonus :"+toatalBonus);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			if(session != null)
				session.close();
		}
	}

	private static void getAllEmployeesDetailsAndSortBasedOnSalary(SessionFactory sf) {
		
		Session session = null;
		
		try {
			session = sf.openSession();
			
			Criteria criteria = session.createCriteria(Employee.class);
			
			criteria.addOrder(Order.desc("salary"));
			
			List<Employee> empList = criteria.list();
			
			for (Employee employee : empList) {
				System.out.println(employee.getEmployeeId()+"\t"+employee.getEmployeeName()+"\t"+employee.getEmail()+"\t"+employee.getSalary()+"\t"+employee.getDeptName());
				
				PanCard panCard = employee.getPanCard();
				System.out.println(panCard.getPancardId()+"\t"+panCard.getPanNo()+"\t"+panCard.getDob());
				
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			if(session != null)
				session.close();
		}
	}

	private static void getEmployeeDetailsById(SessionFactory sf) {
		
		Session session = null;
		int empId=1;
		
		try {
			session = sf.openSession();
			
			Criteria criteria = session.createCriteria(Employee.class);
			
			criteria.add(Restrictions.eq("employeeId", empId));
			
			
			Object object = criteria.uniqueResult();
			if(object != null){
				Employee employee=(Employee)object;
				System.out.println(employee.getEmployeeId()+"\t"+employee.getEmployeeName()+"\t"+employee.getEmail()+"\t"+employee.getSalary()+"\t"+employee.getDeptName());
				
				PanCard panCard = employee.getPanCard();
				if(panCard != null)
				System.out.println(panCard.getPancardId()+"\t"+panCard.getPanNo()+"\t"+panCard.getDob());
				
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			if(session != null)
				session.close();
		}
	}

	private static void selectIdAndEmpName(SessionFactory sf) {
		
		Session session = null;
		
		try {
			session = sf.openSession();
			
			Criteria criteria = session.createCriteria(Employee.class);
			
			ProjectionList projectionList = Projections.projectionList();
			
			projectionList.add(Projections.property("employeeId"));
			projectionList.add(Projections.property("employeeName"));
			
			criteria.setProjection(projectionList);
			
			List<Object[]> list = criteria.list();
			for (Object[] objects : list) {
				int empId=(Integer)objects[0];
				String empName =(String)objects[1];
				
				System.out.println(empId+"\t"+empName);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			if(session != null)
				session.close();
		}
	
	}

	private static void getAllEmployeesDetails(SessionFactory sf) {
		
		Session session = null;
		
		try {
			session = sf.openSession();
			
			Criteria criteria = session.createCriteria(Employee.class);
			
			List<Employee> empList = criteria.list();
			
			for (Employee employee : empList) {
				System.out.println(employee.getEmployeeId()+"\t"+employee.getEmployeeName()+"\t"+employee.getEmail()+"\t"+employee.getSalary()+"\t"+employee.getDeptName());
				
				PanCard panCard = employee.getPanCard();
				System.out.println(panCard.getPancardId()+"\t"+panCard.getPanNo()+"\t"+panCard.getDob());
				
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			if(session != null)
				session.close();
		}
	}

}
