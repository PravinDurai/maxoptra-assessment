package com.maxoptra.assessment.application.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

//Used to make this class to listen to all the 
//exceptions that is happening within this REST API
@ControllerAdvice
public class AppExceptions extends ResponseEntityExceptionHandler {

	// Any methods that is used for handling the exception should be annotated with
	// Exception Handler Annotation
	@ExceptionHandler(value = { Exception.class })
	public ResponseEntity<Object> handelAnyException(Exception ex, WebRequest request) {
		String errorMessageDescription = ex.getLocalizedMessage();
		if (errorMessageDescription == null)
			errorMessageDescription = ex.toString();
		ErrorMessage errorMessage = new ErrorMessage(errorMessageDescription);
		return (new ResponseEntity<>(errorMessage, HttpStatus.INTERNAL_SERVER_ERROR));
	}

	@ExceptionHandler(value = { MyExceptions.class })
	public ResponseEntity<Object> handelAnyException(MyExceptions ex, WebRequest request) {
		String errorMessageDescription = ex.getLocalizedMessage();
		if (errorMessageDescription == null)
			errorMessageDescription = ex.toString();
		ErrorMessage errorMessage = new ErrorMessage(errorMessageDescription);
		return (new ResponseEntity<>(errorMessage, HttpStatus.INTERNAL_SERVER_ERROR));
	}

}
