package com.livingoncodes.spring.web.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;

import com.livingoncodes.spring.web.domain.User;
import com.livingoncodes.spring.web.service.UserService;

public class UniqueEmailImpl implements ConstraintValidator<UniqueEmail, String>{

	@Autowired
	private UserService userService;
	
	@Override
	public void initialize(UniqueEmail constraintAnnotation) {
	}

	
	@Override
	public boolean isValid(String email, ConstraintValidatorContext context) {

		User userBeingValidated = userService.findUserByEmail(email);
		
		// if we can't find the email in DB, then the email is new and unique, return true
		if( userBeingValidated == null) {
			return true;
		}

		// get logged in username
		String loggedInUsername = SecurityContextHolder.getContext().getAuthentication().getName();
		
		
		/**
		 * for new account creation time email check
		 */
		if(loggedInUsername.equals("anonymousUser")) {
			if( userService.emailExists(email)) {
				return false;
			}
			return true;
		}
		
		User loggedInUser = userService.getUser(loggedInUsername);

		if(userBeingValidated.getId() == loggedInUser.getId()) {
			return true;
		}

		return false;
	}

}
