package com.isigntech.edgeservice.validator;

import java.awt.RenderingHints.Key;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.isigntech.edgeservice.entity.Users;
import com.isigntech.edgeservice.exception.PasswordSizeException;
import com.isigntech.edgeservice.exception.UserAlreadyInDatabaseException;

@Component
public class PasswordValidator {
	
	@Value("${password.minsize}")
	private int minSize;
	@Value("${password.maxsize}")
	private int maxSize;
	
	public void passwordSizeChecker(String password) {
		try {
		int passwordSize=password.length();
		if(passwordSize<8 || passwordSize>20) {
			throw new PasswordSizeException("Password size should in between "+minSize+" to "+maxSize);
		}
		}catch (NullPointerException e) {
			throw new PasswordSizeException("Password should not be null");
		}
	}
	

}
