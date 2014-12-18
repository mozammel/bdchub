package com.livingoncodes.spring.web.dao;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
@Component("userProfileDao")
public class UserProfileDao {


	@Autowired
	private SessionFactory sessionFactory;
	
	
	public Session session() {
		return sessionFactory.getCurrentSession();
	}

	@Transactional
	public void create(UserProfile userProfile) {

		session().save(userProfile);
		
	}

	public UserProfile getUserProfile(User user) {
		Criteria crit = session().createCriteria(UserProfile.class);
		
		crit.add(Restrictions.idEq(user.getUserProfile().getId()));
		
		UserProfile userProfile = (UserProfile) crit.uniqueResult();

		return userProfile;
	}
	
}
