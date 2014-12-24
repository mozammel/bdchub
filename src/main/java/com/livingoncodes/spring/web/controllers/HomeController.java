package com.livingoncodes.spring.web.controllers;

import java.security.Principal;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.livingoncodes.spring.web.domain.User;
import com.livingoncodes.spring.web.service.UserService;

@Controller
public class HomeController {

	private static Logger logger = LoggerFactory.getLogger(ProfileController.class);
	
	@Autowired
	private UserService userService;
	
	@RequestMapping("/")
	public String  showHome(Model model, Principal principal) {
		
		if( principal != null) {
			User user = userService.getUser(principal.getName());
			
			if(user != null) {
				String fbLink = user.getUserProfile().getFacebookProfile();
				if(fbLink.length() > 0) {
					// get username from fb link
					String facebookUserName = fbLink.substring(fbLink.lastIndexOf("/")+1);
	
					
					model.addAttribute("facebookUserName", facebookUserName);
				}
			}
		}
		
		return "home";
	}
}
