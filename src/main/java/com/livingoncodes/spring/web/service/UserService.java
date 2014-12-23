package com.livingoncodes.spring.web.service;

import com.livingoncodes.spring.web.dao.UserDao;
import com.livingoncodes.spring.web.domain.User;
import org.apache.commons.lang.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Calendar;
import java.util.List;

@Transactional
@Service
public class UserService {

    @Autowired
    private UserDao userDao;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public void create(User user) {

        user.setPassword(passwordEncoder.encode(user.getPassword()));

        user.getUserProfile().setSecret(RandomStringUtils.randomAlphanumeric(16));

        if (user.getUserProfile().getBirthDate() != null) {
            Calendar cal = Calendar.getInstance();
            cal.setTime(user.getUserProfile().getBirthDate());
            cal.add(Calendar.HOUR_OF_DAY, 22);
            user.getUserProfile().setBirthDate(cal.getTime());
        }

        if (!emailExists(user.getEmail())) {
            userDao.save(user);
        }
    }

    public boolean exists(final String username) {
        List<User> users = userDao.findByUserName(username);

        return users != null && users.size() > 0;
    }

    public User getUser(String username) {
        List<User> users = userDao.findByUserName(username);
        if (users != null && users.size() > 0) {

            return users.get(0);
        }

        return null;
    }

    @Secured("ROLE_ADMIN")
    public List<User> getAllUsers() {

        return userDao.findAll();
    }

    public void update(User user) {

        if (user.getPassword().length() > 3) {
            user.setPassword(passwordEncoder.encode(user.getPassword()));
        } else {
            user.setPassword(getPasswordById(user.getId()));
        }

        if (user.getUserProfile().getBirthDate() != null) {
            Calendar cal = Calendar.getInstance();
            cal.setTime(user.getUserProfile().getBirthDate());
            cal.add(Calendar.HOUR_OF_DAY, 22);
            user.getUserProfile().setBirthDate(cal.getTime());
        }

        userDao.save(user);
    }

    private String getPasswordById(Long id) {
        User retrieved = userDao.findOne(id);

        return retrieved.getPassword();
    }

    public boolean emailExists(String email) {
        List<User> users = userDao.findUserByEmail(email);

        return users != null && users.size() > 0;
    }


    public User findUserByEmail(String email) {
        List<User> users = userDao.findUserByEmail(email);

        if (users != null && users.size() > 0) {
            return users.get(0);
        }

        return null;
    }
}
