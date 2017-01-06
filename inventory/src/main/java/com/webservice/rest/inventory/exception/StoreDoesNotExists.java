package com.webservice.rest.inventory.exception;

public class StoreDoesNotExists extends RuntimeException {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public StoreDoesNotExists(String message){
		super(message);
	}
}
