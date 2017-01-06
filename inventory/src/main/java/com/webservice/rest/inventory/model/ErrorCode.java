package com.webservice.rest.inventory.model;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class ErrorCode {
	String errorCode;
	String message;
	
	public ErrorCode(){}
	
	public ErrorCode(String errorCode, String message) {
		super();
		this.errorCode = errorCode;
		this.message = message;
	}

	public String getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
}
