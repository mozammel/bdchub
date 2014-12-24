package com.livingoncodes.spring.web.service;

import com.livingoncodes.spring.web.dao.StatusDao;
import com.livingoncodes.spring.web.domain.Status;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
@Service
public class StatusService {

    @Autowired
    private StatusDao statusDao;

    public List<Status> getCurrent() {
        return statusDao.getStatuses();
    }

    @Secured({"ROLE_USER", "ROLE_ADMIN"})
    public void create(Status status) {
        statusDao.save(status);
    }

    public boolean hasStatus(String name) {
        if (name == null) return false;

        List<Status> statuses = statusDao.getStatuses(name);

        if (statuses.size() == 0) return false;

        return true;
    }

    public Status getStatus(String username) {

        if (username == null) {

            return null;
        }
        List<Status> statuses = statusDao.getStatuses(username);

        if (statuses != null && statuses.size() > 0) {

            return statuses.get(0);
        }

        return null;
    }

    public void saveOrUpdate(Status status) {

        statusDao.save(status);
    }

    public void delete(Long id) {

        statusDao.delete(id);
    }

}
