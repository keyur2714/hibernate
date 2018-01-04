package com.hibernate.client;

import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.transform.Transformers;

import com.hibernate.util.HibernateUtil;
import com.hibernate.vo.EmployeeVO;

public class ClientTest {

	public static void main(String[] args) {
		Session session = null;
		SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
		try {
			session = sessionFactory.openSession();
			SQLQuery query = session.createSQLQuery("call getEmployeeInfoById(:empId)");
			
			query.setParameter("empId", 2);
			query.setResultTransformer(Transformers.aliasToBean(EmployeeVO.class));
			Object object = query.uniqueResult();
			EmployeeVO employeeVO=(EmployeeVO)object;
			
			System.out.println(employeeVO.getEmpid()+"\t"+employeeVO.getDeptName()+"\t"+employeeVO.getEmail()+"\t"+employeeVO.getEmployeeName()+"\t"+employeeVO.getSalary()+"\t"+employeeVO.getDoj()+"\t"+employeeVO.getBonus());
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			if(session != null)
				session.close();
		}
	}
}
