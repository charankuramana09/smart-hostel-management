package com.isigntech.edgeservice.exception;

public class UserAlreadyInDatabaseException extends RuntimeException{
	public UserAlreadyInDatabaseException(String message) {
		super(message);
	}
}
