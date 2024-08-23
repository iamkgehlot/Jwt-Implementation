package com.socialapp.autheticationservice.exceptionHandler;

import java.sql.SQLIntegrityConstraintViolationException;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

@RestControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(DataIntegrityViolationException.class)
	public ResponseEntity<ErrorResponse> DataIntegrityViolationExceptionHandler(
			DataIntegrityViolationException ex) {
		ErrorResponse errorResponse = new ErrorResponse("username already exists", HttpStatus.CONFLICT.value());
		return new ResponseEntity<>(errorResponse, HttpStatus.CONFLICT);
	}
//
//	@ExceptionHandler(Exception.class)
//	public ResponseEntity<ErrorResponse> handleGlobalException(Exception ex) {
//		ErrorResponse errResp = new ErrorResponse("Unknown Error Occurred , Kindly contact admin",
//				HttpStatus.CONFLICT.value());
//		return new ResponseEntity<>(errResp, HttpStatus.CONFLICT);//
//	}

}
