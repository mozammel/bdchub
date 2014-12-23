package com.livingoncodes.spring.web.test.tests;

import java.io.FileInputStream;
import java.io.InputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Iterator;

import javax.sql.DataSource;

import org.apache.commons.lang.RandomStringUtils;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
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
		"classpath:spring/dao-context.xml",
		"classpath:spring/security-context.xml",
		"classpath:com/livingoncodes/spring/web/test/config/datasource.xml" })
@RunWith(SpringJUnit4ClassRunner.class)
public class UserDaoTests {

	@Autowired
	private UserDao userDao;

	
	
	@Autowired
	private UserProfileDao userProfileDao;

	@Autowired
	private DataSource dataSource;

	// private User user1 = new User("hillol", "Hillol Sharkar", "hellothere",
	// "hillol@livingoncodes.com",
	// true, "ROLE_USER");
	// private User user2 = new User("mozammel", "Mozammel Haque", "hellohello",
	// "mozammel@livingoncodes.com",
	// true, "ROLE_ADMIN");
	// private User user3 = new User("imran", "Hillol Sharkar", "thelionking",
	// "imran@livingoncodes.com",
	// true, "ROLE_USER");
	// private User user4 = new User("qasif", "Hillol Sharkar",
	// "showmethemoney", "qasif@livingoncodes.com",
	// false, "user");

	@Before
	public void init() {
		JdbcTemplate jdbc = new JdbcTemplate(dataSource);

		jdbc.execute("delete from status");
		jdbc.execute("delete from user");
		jdbc.execute("delete from user_profile");

	}

	@Test
	public void testCreateUser() {
		UserProfile userProfile = new UserProfile();
		userProfile.setMobileNo("017138383");
		User user = new User("test", "Test Haque", "hellohello",
				"mozammel@livingoncodes.com", true, "ROLE_USER");
		user.setUserProfile(userProfile);
		userDao.create(user);
	}

	@Test
	public void testRetrive() {
		UserProfile userProfile = new UserProfile();
		userProfile.setMobileNo("017138383");
		User user = new User("test", "Test Haque", "hellohello",
				"mozammel@livingoncodes.com", true, "ROLE_USER");
		user.setUserProfile(userProfile);
		userDao.create(user);

		User retrived = userDao.getUser(user.getUsername());
		System.out.println("**************** Mobile: "
				+ retrived.getUserProfile().getMobileNo());
	}

	@Test
	public void testBiDirectional() {
		UserProfile userProfile = new UserProfile();
		userProfile.setMobileNo("017138383");
		User user = new User("test", "Test Haque", "hellohello",
				"mozammel@livingoncodes.com", true, "ROLE_USER");
		user.setUserProfile(userProfile);
		userDao.create(user);

		UserProfile retrived = userProfileDao.getUserProfile(user);
		System.out.println("**************** Username: "
				+ retrived.getUser().getUsername());

	}

	@Test
	public void testDocumentRead() {

		try {
			InputStream inp = new FileInputStream(
					"test/BDCyclistsRegistrationForm.xlsx");
			// InputStream inp = new FileInputStream("workbook.xlsx");

			Workbook wb = WorkbookFactory.create(inp);
			Sheet sheet = wb.getSheetAt(0);

			Iterator<Row> rowIterator = sheet.iterator();
			Row row = rowIterator.next();

			int count = 0;
			while (rowIterator.hasNext()) {
				row = rowIterator.next();

				
				try {
						String fullname = CellValueUtil.getStringValue(row.getCell(1));
						String birthdate = CellValueUtil.getStringValue(row.getCell(2));
						String email = CellValueUtil.getStringValue(row.getCell(3));

						String mobile = CellValueUtil.getStringValue(row.getCell(4));
						String bloodgroup = CellValueUtil.getStringValue(row.getCell(6));;
						String address = CellValueUtil.getStringValue(row.getCell(7));;
						// skip 7, 8, 9, 10, 11

						String emergency = CellValueUtil.getStringValue(row.getCell(13));;

						String sex = CellValueUtil.getStringValue(row.getCell(16));;
						
						System.out.println("Fullname: " + fullname);
						System.out.println("Birthdate: " + birthdate);
						System.out.println("email: " + email);
						System.out.println("mobile: " + mobile);
						System.out.println("Bloodgroup: " + bloodgroup);
						System.out.println("Address: " + address);
						System.out.println("Emergency:" + emergency);
						System.out.println("Sex:" + sex);
						
						UserProfile userProfile = new UserProfile();
						
						
						try {
							userProfile.setBirthDate(new SimpleDateFormat("dd/MM/yyyy").parse(birthdate));
						} catch(ParseException ex) {
							try {
								userProfile.setBirthDate(new SimpleDateFormat("dd.MM.yyyy").parse(birthdate));
							} catch(ParseException ext) {
								try {
									userProfile.setBirthDate(new SimpleDateFormat("d.M.yyyy").parse(birthdate));
								} catch(ParseException ex3) {
									try {
										userProfile.setBirthDate(new SimpleDateFormat("d/M/yyyy").parse(birthdate));
									} catch(ParseException ex4) {
										System.out.println(ex4);
									}
								}
							}
						}
						userProfile.setAddress(address);
						userProfile.setMobileNo(mobile);
						userProfile.setBloodGroup(bloodgroup);
						userProfile.setEmergency(emergency);
						
						userProfile.setSex(sex.equalsIgnoreCase("Male") ? 0 : 1);
						
						// create temp password
						
						String password = RandomStringUtils.randomAlphanumeric(6); 
						userProfile.setTemppass(password);
						
						userProfile.setSecret(RandomStringUtils.randomAlphanumeric(16));
						
						User user = new User("bdcyclist" + count++, fullname, password,
								email, true, "ROLE_USER");
						user.setUserProfile(userProfile);


						userDao.create(user);
				} catch (Exception ex) {
					ex.printStackTrace();
				}


			}

		} catch (Exception ex) {
			ex.printStackTrace();
		}

	}

}
