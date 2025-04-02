package com.springboot.rest_api.config;

import org.springframework.http.HttpStatusCode;
 import org.springframework.web.ErrorResponse;
 import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.springboot.rest_api.exception.InvalidIDException;

@RestControllerAdvice
public class GlobalExceptionHandlerConfig {

	 @ExceptionHandler(InvalidIDException.class)
	 public ErrorResponse invalidIDExceptionHandler(InvalidIDException e) {
		 return ErrorResponse.create
				 			(e, 
				 			HttpStatusCode.valueOf(400), 
				 			e.getMessage()); 
	 }
}
