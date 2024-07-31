package com.isigntech.edgeservice.exception;

public class InactiveUserException extends RuntimeException{
	public InactiveUserException(String message) {
		super(message);
	}
}
