package com.service;

import com.dao.UserDao;
import com.entity.User;

public class UserService {

	private UserDao userDao;
	
	public User login(User user) {
		return userDao.login(user);
	}
	
}
