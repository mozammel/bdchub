package com.livingoncodes.spring.web.controllers;

import java.util.List;

import org.apache.commons.validator.routines.EmailValidator;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.livingoncodes.spring.web.dao.FormValidationGroup;
import com.livingoncodes.spring.web.dao.User;
import com.livingoncodes.spring.web.service.UserService;

@Controller
public class LoginController {
	
	private static Logger logger = Logger.getLogger(LoginController.class);


	private UserService usersService;

	@Autowired
	private MailSender mailSender;
	
	@Autowired
	public void setUsersService(UserService usersService) {
		this.usersService = usersService;
	}

	@RequestMapping("/login")
	public String showLogin() {
		return "login";
	}
	
	@RequestMapping("/denied")
	public String showDenied() {
		return "denied";
	}

	@RequestMapping("/loggedout")
	public String showLoggedOut() {
		return "loggedout";
	}

	@RequestMapping("/forgotpassword")
	public String showForgotPassword() {
		return "forgotpassword";
	}

	@RequestMapping("/newaccount")
	public String showNewAccount(Model model) {
		model.addAttribute("user", new User());
		return "newaccount";
	}

	@RequestMapping("/admin")
	public String showAdmin(Model model) {
		
		List<User> users = usersService.getAllUsers();

		model.addAttribute("users", users);

		return "admin";
	}
	

	private String sendMessage(String email) {
		
		User user = usersService.getUserByEmail(email);
		
		String link = "test secret: " + user.getUserProfile().getSecret();
		
		SimpleMailMessage mail = new SimpleMailMessage();
		mail.setFrom("no-reply@bdcyclists.com");
		mail.setTo(email);
		mail.setSubject("BDCyclists Password Recovery");
		mail.setText("To reset your password, got to the following link: " + link);
		
		try {
			mailSender.send(mail);
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("cant send message");
		}
		
		return "home";
	}

	@RequestMapping(value = "/createaccount", method = RequestMethod.POST)
	public String doCreate(@Validated(FormValidationGroup.class) User user, BindingResult result) {

		if (result.hasErrors()) {
			return "newaccount";
		}

		user.setAuthority("ROLE_USER");
		user.setEnabled(true);

		if (usersService.exists(user.getUsername())) {
			result.rejectValue("username", "DuplicateKey.user.username");
			return "newaccount";
		}

		try {
			usersService.create(user);
		} catch (DuplicateKeyException e) {
			result.rejectValue("username", "DuplicateKey.user.username");
			return "newaccount";
		}

		return "accountcreated";
	}
	
	@RequestMapping(value = "/doforgotpassword", method = RequestMethod.POST)
	public String doForgotPassword(@RequestParam("email") String email) {
		
		logger.debug("Email: " + email);
		
		if( !usersService.emailExists(email)) {
			return "emailnotfound";
		}

		sendMessage(email);
		return "forgotPasswordEmailSent";
	}
}
