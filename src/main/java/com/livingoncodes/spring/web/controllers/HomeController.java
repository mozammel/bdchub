package com.livingoncodes.spring.web.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {

	private static Logger logger = LoggerFactory.getLogger(ProfileController.class);
	
	@RequestMapping("/")
	public String  showHome() {
		
		logger.info("Showing home...");
		
		return "home";
	}
}
