package com.db;

import java.io.Serializable;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class MyHibernateSessionFactory implements Serializable{

	private static final String CONFIG_FILE_LOCATION = "/hibernate.cfg.xml";
	private static final ThreadLocal<Session> threadLocal = new ThreadLocal<Session>();
	private static Configuration configuration = new Configuration();
	private static SessionFactory sessionFactory = null;
	private static String configFile = CONFIG_FILE_LOCATION;
	//static 代码块只会执行一次，在实例化的时候执行
	static {
		try{
			configuration.configure(configFile);
			sessionFactory = configuration.buildSessionFactory();
		} catch (Exception e) {
			System.out.println("%%%% Error Createing SessionFactory %%%%");
			e.printStackTrace();
		}
	}
	//获取Session
	public static Session getCurrentSession() {
		Session session = threadLocal.get();
		
		try {
			//判断Session是否为空，如果为空，将创建一个Session，并赋给线程变量
			if(session == null && !session.isOpen()) {
				if(sessionFactory ==null) {
					rbulidSessionFactory();
				} else {
					session = sessionFactory.openSession();
				}
			}
			threadLocal.set(session);
		} catch(Exception e) {
			
		} 
		return session;
	}
	public static void rbulidSessionFactory() {
		try {
			configuration.configure(configFile);
			sessionFactory = configuration.buildSessionFactory();
		} catch (Exception e) {
			System.out.println("%%%% Error Createing SessionFactory %%%%");
			e.printStackTrace();
		}
		
	} 
}
