package com.webservice.rest.persons.exception;

public class NotANumberException extends RuntimeException {

	
	private static final long serialVersionUID = 1L;

	public NotANumberException(String message){
		super(message);
	}

}
