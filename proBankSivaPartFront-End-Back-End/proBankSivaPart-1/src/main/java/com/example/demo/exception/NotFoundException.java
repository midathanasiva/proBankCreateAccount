package com.example.demo.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND)
/*
 * this class handle Exceptions ,if the http responce is of NotFound type
 */

public class NotFoundException extends RuntimeException {
	private static final long serialVersionUID = 1L;
	
    public NotFoundException(String message) {
        super(message);
    }

    public NotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
    /*
	 * to handle GLobal Exceptions we use this @RestControllerAdvice
	 * 
	 * @ErrorHandler excepts value where to or to which class it need to find for
	 * errors 
	 * Based of the states this exception class works
	 */
}
