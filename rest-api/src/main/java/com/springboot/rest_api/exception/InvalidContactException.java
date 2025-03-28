package com.springboot.rest_api.exception;

public class InvalidContactException  extends Exception{

	private static final long serialVersionUID = 1L;

	private String message;
	
	
	public InvalidContactException(String message) {
		super();
		this.message = message;
	}


	@Override
	public String getMessage() {
		return message;
	}
}

 
