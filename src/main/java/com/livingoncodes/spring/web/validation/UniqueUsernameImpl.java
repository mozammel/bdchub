package com.livingoncodes.spring.web.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;

import com.livingoncodes.spring.web.service.UserService;

public class UniqueUsernameImpl implements ConstraintValidator<UniqueUsername, String>{


	@Autowired
	private UserService userService;
	
	@Override
	public void initialize(UniqueUsername constraintAnnotation) {
		
	}

	@Override
	public boolean isValid(String username, ConstraintValidatorContext context) {
		String loggedInUsername = SecurityContextHolder.getContext().getAuthentication().getName();
		
	
		if(!userService.exists(username)) {
			return true;
		}

		
		if(loggedInUsername.equals(username)) {
			return true;
		}

		return false;
	}

}
