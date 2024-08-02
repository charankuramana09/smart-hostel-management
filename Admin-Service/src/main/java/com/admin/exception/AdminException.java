package com.admin.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class AdminException {
	
	@ExceptionHandler(OtpMissMatchException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public ResponseEntity<String> handlePasswordMissMatchException(OtpMissMatchException ex) {
		return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
	}
	
	  @ExceptionHandler(InvalidEmailFormatException.class)
	  @ResponseStatus(HttpStatus.BAD_REQUEST)
	    public ResponseEntity<String> handleInvalidEmailFormatException(InvalidEmailFormatException ex) {
	        return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
	    }
}

