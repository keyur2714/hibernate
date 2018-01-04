package com.hibernate.client;

import java.math.BigDecimal;
import java.util.Date;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import com.hibernate.entities.Employee;
import com.hibernate.entities.PanCard;
import com.hibernate.util.HibernateUtil;

public class ClientTest {

	public static void main(String[] args) {

		SessionFactory sf = HibernateUtil.getSessionFactory();
		Session session = null;
		Transaction transaction = null;

		try {
			session = sf.openSession();

			transaction = session.beginTransaction();

			Employee employee = new Employee();
			employee.setDeptName("IT");
			employee.setDoj(new Date());
			employee.setEmail("keyur.555kn@gmail.com");
			employee.setEmployeeName("Keyur");
			employee.setSalary(80000.2714d);

			employee.setBonus(new BigDecimal("700.2783"));

			PanCard panCard = new PanCard();
			panCard.setPanNo("AMUPT4011A");

			panCard.setDob(HibernateUtil.getDoj("18/11/2013"));

			//employee.setPanCard(panCard);
			//panCard.setEmployee(employee);

			session.save(employee);
			// session.save(panCard);

			transaction.commit();
		} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}
			e.printStackTrace();
		} finally {
			if (session != null) {
				session.close();
			}
		}

	}

}
