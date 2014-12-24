package com.livingoncodes.spring.web.dao;

import com.livingoncodes.spring.web.domain.UserProfile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserProfileDao extends JpaRepository<UserProfile, Long> {
}
