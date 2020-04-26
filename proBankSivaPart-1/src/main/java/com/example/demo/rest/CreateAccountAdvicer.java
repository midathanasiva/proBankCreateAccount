package com.example.demo.rest;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import com.example.demo.exception.BankException;
import com.example.demo.exception.ErrorInfo;

@RestControllerAdvice
public class CreateAccountAdvicer {

	@ExceptionHandler(value= {BankException.class})
	@ResponseStatus(code=HttpStatus.BAD_REQUEST)
	public ErrorInfo  handelException(BankException ex) {
		return new ErrorInfo(ex.getMessage());
	}
}


