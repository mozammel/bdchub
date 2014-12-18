package com.livingoncodes.spring.web.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Service;

import com.livingoncodes.spring.web.dao.User;
import com.livingoncodes.spring.web.dao.UserDao;

@Service("usersService")
public class UserService {
	
	private UserDao userDao;

	
	@Autowired
	public void setUsersDao(UserDao usersDao) {
		this.userDao = usersDao;
	}



	public void create(User user) {
		userDao.create(user);
		
	}



	public boolean exists(String username) {
		return userDao.exists(username);
	}

	
	public User getUser(String username) {
		return userDao.getUser(username);
	}

	@Secured("ROLE_ADMIN")
	public List<User> getAllUsers() {
		return userDao.getAllUsers();
	}



}