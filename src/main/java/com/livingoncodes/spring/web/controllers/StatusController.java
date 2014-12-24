package com.livingoncodes.spring.web.controllers;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.livingoncodes.spring.web.dao.FormValidationGroup;
import com.livingoncodes.spring.web.domain.Status;
import com.livingoncodes.spring.web.domain.User;
import com.livingoncodes.spring.web.service.StatusService;
import com.livingoncodes.spring.web.service.UserService;

@Controller
public class StatusController {
	
	private StatusService statusService;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	public void setStatusService(StatusService statusService) {
		this.statusService = statusService;
	}


	@RequestMapping("/test")
	public String  showTest(Model model, @RequestParam("id") String id) {
		
		System.out.println("Id is: " + id);

		return "home";
	}
	
	
	@RequestMapping("/statuses")
	public String  showStatuses(Model model, Principal principal) {
		List<Status> statuses = statusService.getCurrent();
		
		model.addAttribute("statuses", statuses);

		return "statuses";
		
	}
	
	@RequestMapping("/createstatus")
	public String  createStatus(Model model, Principal principal) {
		
		Status status = null;
		
		if( principal != null ) {
			String username = principal.getName();
			
			status = statusService.getStatus(username);
		}
		
		if( status == null ) {
			status = new Status();
		}
		
		model.addAttribute(status);
		
		return "createstatus";
	}
	
	@RequestMapping(value="/docreate", method=RequestMethod.POST)
	public String doCreate(Model model, @Validated(value=FormValidationGroup.class) Status status, BindingResult result, Principal principal,
			@RequestParam(value="delete", required=false) String delete) {
		
		if( result.hasErrors() ) {
			return "createstatus";
		}
		
		if(delete == null) {
			String username = principal.getName();
			User loggedInUser = userService.getUser(username);
			status.getUser().setId(loggedInUser.getId());
			statusService.saveOrUpdate(status);
			return "statuscreated";
		} else {
			statusService.delete(status.getId());
			return "statusdeleted";
		}
	}
}
