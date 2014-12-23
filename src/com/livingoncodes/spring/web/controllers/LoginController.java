package com.livingoncodes.spring.web.controllers;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.RandomStringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.livingoncodes.spring.web.dao.FormValidationGroup;
import com.livingoncodes.spring.web.dao.PasswordResetValidationGroup;
import com.livingoncodes.spring.web.dao.User;
import com.livingoncodes.spring.web.service.UserService;

@Controller
public class LoginController {
	
	private static Logger logger = Logger.getLogger(LoginController.class);


	private UserService usersService;

	// TODO: remove mailSender properties from xml to private property file
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
	

	private String sendMessage(User user, HttpServletRequest request) {

		String firstPart = request.getRequestURL().substring(0, 
				request.getRequestURL().length() - request.getRequestURI().length());
		String context = request.getContextPath();
		
		String link =  firstPart + context + "/reset?email=" + 
				user.getEmail() + "&secret=" + user.getUserProfile().getSecret();

		System.out.println(link);
		
		
		SimpleMailMessage mail = new SimpleMailMessage();
		mail.setFrom("no-reply@bdcyclists.com");
		mail.setTo(user.getEmail());
		mail.setSubject("BDCyclists Password Recovery");
		mail.setText("Your username is: " + user.getUsername() + ". You will need this when you try to login.\n\nTo reset your password, got to the following link: \n\n" + link);
		
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
	public String doForgotPassword(@RequestParam("email") String email, HttpServletRequest request) {
		
		logger.debug("Email: " + email);
		
		if( !usersService.emailExists(email)) {
			return "emailnotfound";
		}

		User user = usersService.getUserByEmail(email);
		sendMessage(user, request);
		return "forgotPasswordEmailSent";
	}
	
	@RequestMapping(value = "/reset", method = RequestMethod.GET)
	public String doReset(@RequestParam("email") String email, @RequestParam("secret") String secret, Model model) {
		
		User user = usersService.getUserByEmail(email);
		
		
		if(user != null) {
			System.out.println("got user, checking secret");
			if(user.getUserProfile().getSecret().equals(secret)) {
				
				model.addAttribute("user", user);
				model.addAttribute("secret", secret);
				
				
				return "resetpassword";
				
			}
			else {
				return "keyerror";
			}
		}
		else {
			System.out.println("user not found with this email");
		}
		
		return "forgotPasswordEmailSent";
	}

	@RequestMapping(value = "/doresetpassword", method = RequestMethod.POST)
	public String doResetPassword(@Validated(PasswordResetValidationGroup.class) User user, BindingResult result) {

		
		if (result.hasErrors()) {
			return "resetpassword";
		}
		
		user.getUserProfile().setSecret(RandomStringUtils.randomAlphanumeric(16));
		usersService.update(user);

		return "passwordchanged";
	}

}

