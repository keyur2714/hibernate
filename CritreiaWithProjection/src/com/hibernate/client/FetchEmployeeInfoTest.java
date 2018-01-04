package com.hibernate.client;

import java.util.Scanner;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.ProjectionList;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.transform.Transformers;

import com.hibernate.entities.Employee;
import com.hibernate.util.HibernateUtil;
import com.hibernate.vo.EmployeeVO;

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
			
			Criteria criteria = session.createCriteria(Employee.class);
			
			criteria.createAlias("panCard", "panInfo");
			criteria.add(Restrictions.eq("employeeId", employeeId));
			
			
			ProjectionList projectionList = Projections.projectionList();
			projectionList.add(Projections.property("employeeId"),"employeeId");
			projectionList.add(Projections.property("salary"),"salary");
			projectionList.add(Projections.property("employeeName"),"employeeName");
			projectionList.add(Projections.property("panInfo.panNo"),"panNo");
			projectionList.add(Projections.property("panInfo.dob"),"dob");
			
			criteria.setProjection(projectionList);
			
			criteria.setResultTransformer(Transformers.aliasToBean(EmployeeVO.class));
			
			Object object = criteria.uniqueResult();
			
			if(object != null){
				EmployeeVO employeeVO =(EmployeeVO)object;
				
				System.out.println(employeeVO.getEmployeeId()+"\t"+employeeVO.getEmployeeName()+"\t"+employeeVO.getPanNo());
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
