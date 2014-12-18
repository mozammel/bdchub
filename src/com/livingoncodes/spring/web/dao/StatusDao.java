package com.livingoncodes.spring.web.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
@Component("statusDao")
public class StatusDao {

	
	@Autowired
	private SessionFactory sessionFactory;
	
	
	public Session session() {
		return sessionFactory.getCurrentSession();
	}
	

	
	@SuppressWarnings("unchecked")
	public List<Status> getStatuses() {

		Criteria crit = session().createCriteria(Status.class);
		
		crit.createAlias("user", "u").add(Restrictions.eq("u.enabled", true));
		
		return crit.list();

	}
	
	@SuppressWarnings("unchecked")
	public List<Status> getStatuses(String name) {

		Criteria crit = session().createCriteria(Status.class);
		
		crit.createAlias("user", "u");
		crit.add(Restrictions.eq("u.enabled", true));
		crit.add(Restrictions.eq("u.username", name));
		
		return crit.list();

	}


	public boolean delete(int id) {

		Query query = session().createQuery("delete from Status where id=:id");
		query.setLong("id", id);
		return query.executeUpdate() == 1;

	}
	
	public void saveOrUpdate(Status status) {

		System.out.println("Checking User::::::: " + status.getUser());
		session().saveOrUpdate(status);
	}
	
	
	
	public Status getStatus(int id) {

		Criteria crit = session().createCriteria(Status.class);
		
		crit.createAlias("user", "u");
		
		crit.add(Restrictions.eq("u.enabled", true));
		crit.add(Restrictions.idEq(id));
		
		
		return (Status) crit.uniqueResult();
		

	}

}
