package com.rest.webservices.restfulwebservices.helloworld;

public class HelloWorlBean {

	private String message;

	public HelloWorlBean(String message) {
		// TODO Auto-generated constructor stub
		this.message = message;
	}

	//if get method is not there in bean class -> mapping error will occurred
	//Whitelabel Error Page
	//This application has no explicit mapping for /error, so you are seeing this as a fallback.


	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	@Override
	public String toString() {
		return "HelloWorlBean [message=" + message + "]";
	}

}
