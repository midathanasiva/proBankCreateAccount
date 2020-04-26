package com.example.demo.exception;

public class BankException extends Throwable{

	
	private static final long serialVersionUID = 7120812304308536789L;
	String message;
	
	public BankException(String msg) {
		
		this.message=msg;
	}  
	   
	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

}
