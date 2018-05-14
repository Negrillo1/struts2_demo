package com.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.transaction.annotation.Transactional;

import com.db.HibernateUtils;
import com.db.MyHibernateSessionFactory;
import com.entity.User;

public class UserDaoImpl implements UserDao{

	private HibernateTemplate hibernateTemplate;
	
	public void setHibernateTemplate(HibernateTemplate hibernateTemplate) {
		this.hibernateTemplate = hibernateTemplate;
	}
	@SuppressWarnings("all")
	@Transactional
	public User login(User user) {
		// TODO Auto-generated method stub

			List <User> list = (List<User>) hibernateTemplate.
					find("from User where username = ? and password = ?", user.getUsername(),user.getPassword());
			if(list != null && list.size() != 0) {
				User u = (User) list.get(0);
				return u;
			}
		return null;
	}

	
}
