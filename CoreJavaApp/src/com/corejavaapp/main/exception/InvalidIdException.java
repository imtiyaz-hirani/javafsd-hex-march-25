package com.corejavaapp.main.exception;

public class InvalidIdException extends Exception {

	private static final long serialVersionUID = -1180219638563029139L; 

	private String message;

	public InvalidIdException(String message) {
		super();
		this.message = message;
	}

	public String getMessage() {
		return message;
	} 
	
	
}
