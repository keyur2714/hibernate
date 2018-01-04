package com.hibernate.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateUtil {

	private  static SessionFactory factory = null;
	
	static{
		Configuration cfg = new Configuration();
		cfg.configure();
		
		factory = cfg.buildSessionFactory();
	}
	
	public static SessionFactory getSessionFactory(){
		return factory;
		
	}
	
	public static Date getDoj(String doj) throws ParseException{
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		return dateFormat.parse(doj);
	}
}
