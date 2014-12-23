package com.livingoncodes.spring.web.service;

import com.livingoncodes.spring.web.dao.UserProfileDao;
import com.livingoncodes.spring.web.domain.User;
import com.livingoncodes.spring.web.domain.UserProfile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Calendar;

/**
 * Created by rokonoid on 12/24/14.
 */
@Transactional
@Service
public class UserProfileService {

    @Autowired
    private UserProfileDao userProfileDao;

    public void create(UserProfile userProfile) {

        if (userProfile.getBirthDate() != null) {
            Calendar cal = Calendar.getInstance();
            cal.setTime(userProfile.getBirthDate());
            cal.add(Calendar.HOUR_OF_DAY, 22);
            userProfile.setBirthDate(cal.getTime());
        }

        userProfileDao.save(userProfile);
    }

    public UserProfile findUserProfileByUser(User user) {

        return userProfileDao.findOne(user.getUserProfile().getId());
    }
}
