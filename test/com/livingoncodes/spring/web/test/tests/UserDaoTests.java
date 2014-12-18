package com.livingoncodes.spring.web.test.tests;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Iterator;

import javax.sql.DataSource;

import org.apache.poi.ss.usermodel.Cell;
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
		"classpath:com/livingoncodes/spring/web/config/dao-context.xml",
		"classpath:com/livingoncodes/spring/web/config/security-context.xml",
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

	// @Test
	// public void testCreateUser() {
	// UserProfile userProfile = new UserProfile();
	// userProfile.setMobileNo("017138383");
	// User user = new User("test", "Test Haque", "hellohello",
	// "mozammel@livingoncodes.com", true, "ROLE_USER");
	// user.setUserProfile(userProfile);
	// userDao.create(user);
	// }
	//
	// @Test
	// public void testRetrive() {
	// UserProfile userProfile = new UserProfile();
	// userProfile.setMobileNo("017138383");
	// User user = new User("test", "Test Haque", "hellohello",
	// "mozammel@livingoncodes.com", true, "ROLE_USER");
	// user.setUserProfile(userProfile);
	// userDao.create(user);
	//
	//
	// User retrived = userDao.getUser(user.getUsername());
	// System.out.println("**************** Mobile: " +
	// retrived.getUserProfile().getMobileNo());
	// }
	//
	// @Test
	// public void testBiDirectional() {
	// UserProfile userProfile = new UserProfile();
	// userProfile.setMobileNo("017138383");
	// User user = new User("test", "Test Haque", "hellohello",
	// "mozammel@livingoncodes.com", true, "ROLE_USER");
	// user.setUserProfile(userProfile);
	// userDao.create(user);
	//
	// UserProfile retrived = userProfileDao.getUserProfile(user);
	// System.out.println("**************** Username: " +
	// retrived.getUser().getUsername());
	//
	// }

	@Test
	public void testDocumentRead() {

		try {
			InputStream inp = new FileInputStream(
					"test/BDCyclistsRegistrationForm.xlsx");
			// InputStream inp = new FileInputStream("workbook.xlsx");

			Workbook wb = WorkbookFactory.create(inp);
			Sheet sheet = wb.getSheetAt(0);

			Iterator<Row> rowIterator = sheet.iterator();
			while (rowIterator.hasNext()) {
				Row row = rowIterator.next();

				try {
						String fullname = row.getCell(1).getStringCellValue();
						String birthdate = row.getCell(2).getStringCellValue();
						String email = row.getCell(3).getStringCellValue();

						String mobile = "";
						if (row.getCell(4).getCellType() == Cell.CELL_TYPE_NUMERIC) {
							mobile = String.valueOf(row.getCell(4)
									.getNumericCellValue());
						} else {
							mobile = row.getCell(4).getStringCellValue();
						}
						String bloodgroup = row.getCell(5) == null ? "NA":row.getCell(5).getStringCellValue();
						String address = row.getCell(6) == null ? "NA":row.getCell(6).getStringCellValue();
						// skip 7, 8, 9, 10, 11

						String emergency = "";
						if (row.getCell(12).getCellType() == Cell.CELL_TYPE_NUMERIC) {
							emergency = String.valueOf(row.getCell(12)
									.getNumericCellValue());
						} else {
							emergency = row.getCell(12).getStringCellValue();
						}

						UserProfile userProfile = new UserProfile();
						userProfile.setAddress(address);
						userProfile.setMobileNo(mobile);
						userProfile.setBloodGroup(bloodgroup);
						userProfile.setEmergency(emergency);

						User user = new User(email, fullname, "testtest",
								email, true, "ROLE_USER");
						user.setUserProfile(userProfile);


						userDao.create(user);
				} catch (Exception ex) {
					ex.printStackTrace();
				}

				// For each row, iterate through each columns
				// Iterator<Cell> cellIterator = row.cellIterator();
				// while (cellIterator.hasNext()) {
				//
				// Cell cell = cellIterator.next();
				//
				// switch (cell.getCellType()) {
				// case Cell.CELL_TYPE_BOOLEAN:
				// System.out.print(cell.getBooleanCellValue() + "\t\t");
				// break;
				// case Cell.CELL_TYPE_NUMERIC:
				// System.out.print(cell.getNumericCellValue() + "\t\t");
				// break;
				// case Cell.CELL_TYPE_STRING:
				// System.out.print(cell.getStringCellValue() + "\t\t");
				// break;
				// }
				// }
			}

		} catch (Exception ex) {
			ex.printStackTrace();
		}

	}

}
