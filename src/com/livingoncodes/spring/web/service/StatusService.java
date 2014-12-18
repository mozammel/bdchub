package com.livingoncodes.spring.web.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Service;

import com.livingoncodes.spring.web.dao.Status;
import com.livingoncodes.spring.web.dao.StatusDao;

@Service("statusService")
public class StatusService {
	
	private StatusDao statusDao;

	@Autowired
	public void setStatusDao(StatusDao statusDao) {
		this.statusDao = statusDao;
	}
	
	public List<Status> getCurrent() {
		return statusDao.getStatuses();
	}

	@Secured({"ROLE_USER", "ROLE_ADMIN"})
	public void create(Status status) {
		statusDao.saveOrUpdate(status);
		
	}

	public boolean hasStatus(String name) {
		if( name == null )
			return false;
		
		List<Status> statuses = statusDao.getStatuses(name);
		
		if(statuses.size() == 0)
			return false;
		
		
		return true;
	}

	public Status getStatus(String username) {
		
		if( username == null )
			return null;
		
		List<Status> statuses = statusDao.getStatuses(username);
		
		if( statuses.size() == 0 )
			return null;
				
		return statuses.get(0);
	}

	public void saveOrUpdate(Status statis) {
			statusDao.saveOrUpdate(statis);
	}

	public void delete(int id) {
		statusDao.delete(id);
		
	}



}
