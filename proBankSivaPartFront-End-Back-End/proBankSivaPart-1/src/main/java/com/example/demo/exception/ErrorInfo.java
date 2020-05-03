package com.example.demo.exception;

public class ErrorInfo {
	private String message;
	public ErrorInfo(){}
	public ErrorInfo(String message) {
		super();
		this.message = message;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	
	/*
	 * this is a class to handle with error messages the Create AccountAdvicer class
	 * takes this classs help to display the error
	 */
}


