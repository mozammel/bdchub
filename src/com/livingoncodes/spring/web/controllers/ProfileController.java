package com.livingoncodes.spring.web.controllers;

import java.security.Principal;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.livingoncodes.spring.web.dao.FormValidationGroup;
import com.livingoncodes.spring.web.dao.Status;
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
			@Validated(value = FormValidationGroup.class) User user,
			BindingResult result, Principal principal) {

		if (result.hasErrors()) {
			return "profile";
		}

		System.out.println(user);
		
		userService.update(user);
		return "profileupdated";
	}

}
