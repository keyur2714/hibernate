package com.hibernate.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateUtil {

	private static SessionFactory sessionFactory = null;
	
	public static SessionFactory getSessionFactory(){
		try {
			Configuration cfg= new Configuration();
			cfg = cfg.configure("hibernate.cfg.xml");
			
			sessionFactory = cfg.buildSessionFactory();
		} catch (HibernateException e) {
			e.printStackTrace();
		}
		
		return sessionFactory; 
	}
	
	public static Date getDOJ(String doj) {
		
		Date date = null;
		try {
			SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
			date = dateFormat.parse(doj);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return date;
		
	}
}
