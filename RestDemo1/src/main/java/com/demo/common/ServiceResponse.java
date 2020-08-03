package com.demo.common;

public class ServiceResponse {

    String message;

    public ServiceResponse(String message) {
	super();
	this.message = message;
    }

    public String getMessage() {
	return message;
    }

    public void setMessage(String message) {
	this.message = message;
    }

}
