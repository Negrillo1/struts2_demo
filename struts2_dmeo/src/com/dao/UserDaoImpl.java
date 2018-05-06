package com.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.db.HibernateUtils;
import com.db.MyHibernateSessionFactory;
import com.entity.User;

public class UserDaoImpl implements UserDao{

	public User login(User user) {
		// TODO Auto-generated method stub
		Transaction tx = null;
		String hql = "";
		try{

			Session session = HibernateUtils.getSession();
			tx = session.beginTransaction();
			hql = "from User where username = ? and password = ?";
			Query query = session.createQuery(hql);
			query.setParameter(0, user.getUsername());
			query.setParameter(1, user.getPassword());
			List list = query.list();
			tx.commit();
			if(list.size() > 0) {
				User uu = (User) list.get(0);
				return uu;
			}
		} catch(Exception e) {
			e.printStackTrace();
			return null;
		} finally{

			if (tx != null) {
				tx = null;
			}
		}
		return null;
	}

	
}
