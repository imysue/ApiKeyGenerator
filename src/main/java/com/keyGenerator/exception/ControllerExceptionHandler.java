package com.keyGenerator.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestControllerAdvice 
public class ControllerExceptionHandler {
	
	@ExceptionHandler(NullPointerException.class)
	protected ResponseEntity<ErrorResponse> handleNullPointerException(NullPointerException e){
		
		log.error("NullPointerException", e);
		 
		final ErrorResponse response = ErrorResponse.create().status(HttpStatus.INTERNAL_SERVER_ERROR.value()).message(e.getMessage());

	    return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);	
	}

	@ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    protected ResponseEntity<ErrorResponse> handleHttpRequestMethodNotSupportedException(HttpRequestMethodNotSupportedException e) {
        
		log.error("handleHttpRequestMethodNotSupportedException", e);

        final ErrorResponse response = ErrorResponse.create().status(HttpStatus.METHOD_NOT_ALLOWED.value()).message(e.getMessage());

        return new ResponseEntity<>(response, HttpStatus.METHOD_NOT_ALLOWED);
    }
	
	@ExceptionHandler(CustomException.class)
    protected ResponseEntity<ErrorResponse> handleApiException(CustomException e) {

		log.error("CustomException", e);
		
		final ErrorResponse response = ErrorResponse.create().status(e.getHttpStatus().value()).message(e.getMessage());

        return new ResponseEntity<>(response, e.getHttpStatus());
    }
    
    //모든 예외를 핸들링하여 ErrorResponse 형식으로 반환한다.
    @ExceptionHandler(Exception.class)
    protected ResponseEntity<ErrorResponse> handleException(Exception e) {
        
    	log.error("handleException", e);
    	
    	e.printStackTrace();

        ErrorResponse response = ErrorResponse.create().status(HttpStatus.INTERNAL_SERVER_ERROR.value()).message(e.toString());

        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
