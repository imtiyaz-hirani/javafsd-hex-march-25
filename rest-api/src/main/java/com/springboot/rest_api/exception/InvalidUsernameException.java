package com.springboot.rest_api.exception;

public class InvalidUsernameException extends Exception{

	private static final long serialVersionUID = 1L;

	private String message;
	
	
	public InvalidUsernameException(String message) {
		super();
		this.message = message;
	}


	@Override
	public String getMessage() {
		return message;
	}
}
