package com.livingoncodes.spring.web.test.tests;

import javax.sql.DataSource;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.livingoncodes.spring.web.dao.User;
import com.livingoncodes.spring.web.dao.UserDao;
import com.livingoncodes.spring.web.dao.UserProfile;
import com.livingoncodes.spring.web.dao.UserProfileDao;

@ActiveProfiles("dev")
@ContextConfiguration(locations = {
		"classpath:com/livingoncodes/spring/web/config/dao-context.xml",
		"classpath:com/livingoncodes/spring/web/config/security-context.xml",
		"classpath:com/livingoncodes/spring/web/test/config/datasource.xml"})
@RunWith(SpringJUnit4ClassRunner.class)
public class UserDaoTests {
	
	@Autowired
	private UserDao usersDao;
	
	@Autowired
	private UserProfileDao userProfileDao;
	
	@Autowired
	private DataSource dataSource;
	
	
	
//	private User user1 = new User("hillol", "Hillol Sharkar", "hellothere", "hillol@livingoncodes.com",
//			true, "ROLE_USER");
//	private User user2 = new User("mozammel", "Mozammel Haque", "hellohello", "mozammel@livingoncodes.com",
//			true, "ROLE_ADMIN");
//	private User user3 = new User("imran", "Hillol Sharkar", "thelionking", "imran@livingoncodes.com",
//			true, "ROLE_USER");
//	private User user4 = new User("qasif", "Hillol Sharkar", "showmethemoney", "qasif@livingoncodes.com",
//			false, "user");
	
	@Before
	public void init() {
		JdbcTemplate jdbc = new JdbcTemplate(dataSource);

		jdbc.execute("delete from status");
		jdbc.execute("delete from user_profile");
		jdbc.execute("delete from user");
		
	}
	
	
	@Test
	public void testCreateUser() {
		
		UserProfile userProfile = new UserProfile();
		
		
		
		User user = new User("mozammel", "Mozammel Haque", "hellohello", "mozammel@livingoncodes.com", true, "ROLE_USER", userProfile);
		
		
		
		usersDao.create(user);
	}

}
