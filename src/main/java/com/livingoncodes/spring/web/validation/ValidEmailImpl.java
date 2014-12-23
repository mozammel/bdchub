package com.livingoncodes.spring.web.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.apache.commons.validator.routines.EmailValidator;
import org.springframework.beans.factory.annotation.Autowired;

import com.livingoncodes.spring.web.service.UserService;

public class ValidEmailImpl implements ConstraintValidator<ValidEmail, String>{

	private int min;
	
	
	@Override
	public void initialize(ValidEmail constraintAnnotation) {
		min = constraintAnnotation.min(); 
	}

	@Override
	public boolean isValid(String email, ConstraintValidatorContext context) {
		
		if(email.length() < min) {
			return false;
		}
		
		if(!EmailValidator.getInstance(false).isValid(email)) {
			return false;
		}
		
		return true;
	}

}
