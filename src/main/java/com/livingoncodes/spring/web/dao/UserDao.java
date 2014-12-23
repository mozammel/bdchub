package com.livingoncodes.spring.web.dao;

import com.livingoncodes.spring.web.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserDao extends JpaRepository<User, Long> {

    @Query("select o from User o where o.username=:username")
    List<User> findByUserName(@Param("username") String username);

    @Query("select o from User o where o.email=:email")
    List<User> findUserByEmail(@Param("email") String email);
}
