package com.demo.common;

import java.util.Date;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
@RestController
public class ExceptionHandler extends ResponseEntityExceptionHandler {

    @org.springframework.web.bind.annotation.ExceptionHandler
    public final ResponseEntity<Object> handleAllException(Exception ex, WebRequest request) {
	ExceptionResponse response = new ExceptionResponse(new Date(), ex.getMessage(), request.getDescription(false));
	return new ResponseEntity(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @org.springframework.web.bind.annotation.ExceptionHandler
    public final ResponseEntity<Object> handleNotFoundException(UserNotFoundException ex, WebRequest request) {
	ExceptionResponse response = new ExceptionResponse(new Date(), ex.getMessage(), request.getDescription(false));
	return new ResponseEntity(response, HttpStatus.NOT_FOUND);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
	    HttpHeaders headers, HttpStatus status, WebRequest request) {
	ExceptionResponse response = new ExceptionResponse(new Date(), ex.getMessage(),
		ex.getBindingResult().toString());
	return new ResponseEntity(response, HttpStatus.BAD_REQUEST);
    }
}
