package com.livingoncodes.spring.web.dao;

import com.livingoncodes.spring.web.domain.Status;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
public interface StatusDao extends JpaRepository<Status, Long> {

    @Query("select o from Status o where o.user.enabled = true")
    public List<Status> getStatuses();

    @Query("select o from Status o where o.user.enabled = true and o.user.username=:username")
    public List<Status> getStatuses(@Param("username") String username);

}
