package com.livingoncodes.spring.web.dao;

import java.util.Calendar;
import java.util.List;

import org.apache.commons.lang.RandomStringUtils;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
@Component("userDao")
public class UserDao {

	@Autowired
	private PasswordEncoder passwordEncoder;
	

	@Autowired
	private SessionFactory sessionFactory;
	
	
	public Session session() {
		return sessionFactory.getCurrentSession();
	}

	@Transactional
	public void create(User user) {

		user.setPassword(passwordEncoder.encode(user.getPassword()));
		
		user.getUserProfile().setSecret(RandomStringUtils.randomAlphanumeric(16));
		
		if(user.getUserProfile().getBirthDate() != null ) {
			Calendar cal = Calendar.getInstance();
			cal.setTime(user.getUserProfile().getBirthDate());
			cal.add(Calendar.HOUR_OF_DAY, 22);
			user.getUserProfile().setBirthDate(cal.getTime());
		}
		
		if(!emailExists(user.getEmail())) {
			session().save(user);
		}
	}
	
	private String getPasswordById(int id) {
		Session session = sessionFactory.openSession();
		
		Criteria crit = session.createCriteria(User.class);
		
		crit.add(Restrictions.idEq(id));
		
		User retrived = (User) crit.uniqueResult();
		
		session.close();
		
		return retrived.getPassword();
	}
	
	public void update(User user) {

		if(user.getPassword().length() > 3) {
			user.setPassword(passwordEncoder.encode(user.getPassword()));
		} else {
			user.setPassword(getPasswordById(user.getId()));
		}

		if(user.getUserProfile().getBirthDate() != null ) {
			Calendar cal = Calendar.getInstance();
			cal.setTime(user.getUserProfile().getBirthDate());
			cal.add(Calendar.HOUR_OF_DAY, 22);
			user.getUserProfile().setBirthDate(cal.getTime());
		}

		session().update(user);
		
	}
	
	
	public boolean emailExists(String email) {
		
		Criteria crit = session().createCriteria(User.class);
		
		crit.add(Restrictions.eq("email", email));
		
		User user = (User) crit.uniqueResult();
		
		return user != null;
				
	}

	
	public boolean exists(String username) {
		
		Criteria crit = session().createCriteria(User.class);
		
		crit.add(Restrictions.eq("username", username));
		
		User user = (User) crit.uniqueResult();
		
		return user != null;
				
	}

	
	@SuppressWarnings("unchecked")
	public List<User> getAllUsers() {
		
		return session().createQuery("from User").list();
		
		
	}

	public User getUser(String username) {
		Criteria crit = session().createCriteria(User.class);
		
		crit.add(Restrictions.eq("username", username));
		
		User user = (User) crit.uniqueResult();

		return user;
	}

	public User getUserByEmail(String email) {
		Criteria crit = session().createCriteria(User.class);
		
		crit.add(Restrictions.eq("email", email));
		
		User user = (User) crit.uniqueResult();
		
		return user;
	}

	
}
