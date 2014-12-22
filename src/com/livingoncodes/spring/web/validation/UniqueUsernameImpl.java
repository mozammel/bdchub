package com.livingoncodes.spring.web.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.apache.commons.validator.routines.EmailValidator;
import org.springframework.beans.factory.annotation.Autowired;

import com.livingoncodes.spring.web.service.UserService;

public class UniqueUsernameImpl implements ConstraintValidator<UniqueUsername, String>{


	@Autowired
	private UserService userService;
	
	@Override
	public void initialize(UniqueUsername constraintAnnotation) {
		
	}

	@Override
	public boolean isValid(String username, ConstraintValidatorContext context) {
		if(userService.exists(username)) {
			return false;
		}
		
		return true;
	}

}
