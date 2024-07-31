package com.isigntech.edgeservice.exception;

public class PasswordNotMatchException extends RuntimeException
{
	public PasswordNotMatchException(String message) {
		super(message);
	}
}
