package com.example.demo.exception;

public class BankException extends Throwable{
	/*
	 * this class is to handle with exceptions compile time or run time exceptions ,
	 */
	

	
	private static final long serialVersionUID = 1L;
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
