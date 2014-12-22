package com.livingoncodes.spring.web.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;

import com.livingoncodes.spring.web.dao.User;
import com.livingoncodes.spring.web.service.UserService;

public class UniqueEmailImpl implements ConstraintValidator<UniqueEmail, String>{

	@Autowired
	private UserService userService;
	
	@Override
	public void initialize(UniqueEmail constraintAnnotation) {
	}

	@Override
	public boolean isValid(String email, ConstraintValidatorContext context) {
		User user = userService.getUserByEmail(email);
		
		if( user == null) {
			return true;
		}

		String loggedInUsername = SecurityContextHolder.getContext().getAuthentication().getName();
		
		User loggedInUser = userService.getUser(loggedInUsername);
		
		/**
		 * we will have this null only for password reset case
		 */
		if(loggedInUsername.equals("anonymousUser") || loggedInUser == null) {
			return true;
		}

		
		if(user.getId() == loggedInUser.getId()) {
			return true;
		}

		return false;
	}

}
