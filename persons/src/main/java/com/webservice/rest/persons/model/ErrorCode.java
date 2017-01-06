package com.webservice.rest.persons.model;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class ErrorCode {
	int errorCode;
	String message;
	
	public ErrorCode(){}
	
	public ErrorCode(int errorCode, String message) {
		super();
		this.errorCode = errorCode;
		this.message = message;
	}

	public int getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(int errorCode) {
		this.errorCode = errorCode;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
}
