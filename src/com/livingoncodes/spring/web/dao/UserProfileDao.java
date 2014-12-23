package com.livingoncodes.spring.web.dao;

import java.util.Calendar;

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

		if (userProfile.getBirthDate() != null) {
			Calendar cal = Calendar.getInstance();
			cal.setTime(userProfile.getBirthDate());
			cal.add(Calendar.HOUR_OF_DAY, 22);
			userProfile.setBirthDate(cal.getTime());
		}

		session().save(userProfile);

	}

	public UserProfile getUserProfile(User user) {
		Criteria crit = session().createCriteria(UserProfile.class);

		crit.add(Restrictions.idEq(user.getUserProfile().getId()));

		UserProfile userProfile = (UserProfile) crit.uniqueResult();

		return userProfile;
	}

}
