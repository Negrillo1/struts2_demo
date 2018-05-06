package com.db;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;


public class HibernateUtils {

	private static SessionFactory sessionFactory;
	
	public static Session getSession() {
		return getSessionFactory().getCurrentSession();
	}
	
	public static SessionFactory getSessionFactory() {
		//保证sessionFactory为单例的
		if(sessionFactory == null || sessionFactory.isClosed()) {
			sessionFactory = new Configuration().configure().buildSessionFactory();
		}
		return sessionFactory;
	}
	
}
