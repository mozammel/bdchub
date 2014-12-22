package com.livingoncodes.spring.web.controllers;

import java.security.Principal;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.security.web.authentication.rememberme.PersistentTokenBasedRememberMeServices;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.livingoncodes.spring.web.dao.FormValidationGroup;
import com.livingoncodes.spring.web.dao.User;
import com.livingoncodes.spring.web.service.UserService;

@Controller
public class ProfileController {
	private static Logger logger = Logger.getLogger(ProfileController.class);

	@Autowired
	private UserService userService;

	@RequestMapping("/profile")
	public String showProfile(Model model, Principal principal) {

		logger.info("Showing profile...");
		User user = userService.getUser(principal.getName());

		user.setPassword("");

		model.addAttribute("user", user);

		return "profile";
	}

	@RequestMapping(value = "/doupdateprofile", method = RequestMethod.POST)
	public String doCreate(Model model,
			@Validated(value=FormValidationGroup.class) User user,
			BindingResult result, Principal principal, HttpServletRequest request, HttpServletResponse response) {
		
		/**
		 * If the password entered on the profile update form is blank
		 * allow saving the user. This special condition is handled at
		 * UserDao where we check if password is size 0, we get the current
		 * password from DB and populate the user bean before saving it
		 */
		
		System.out.println("Model:::::::::::::::::::::::" + model);
		


		if (result.hasErrors()) {
			return "profile";
		}

		userService.update(user);
		
		/**
		 * Check if username has changed, then force logout
		 * TODO: Update principal name programmatically, then you don't need to logout
		 */
		if( !user.getUsername().equals(principal.getName())) {
			Authentication auth = SecurityContextHolder.getContext().getAuthentication();
	          if (auth != null){    
	             new SecurityContextLogoutHandler().logout(request, response, auth);
	          }
	        SecurityContextHolder.getContext().setAuthentication(null);
	        return "forcedloggedout";
		}
		
		return "profileupdated";
	}

}
