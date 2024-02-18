package com.jsp.ferro.alloy.controller;

import java.time.Instant;
import java.time.LocalDateTime;
import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.jsp.ferro.alloy.exception.CustomException;
import com.jsp.ferro.alloy.exception.RequestException;
import com.jsp.ferro.alloy.exception.UserCustomException;

/**
 * @author Sajan Yadav
 * @apiNote Handle globel exception
 * @Date 12 Sept 2023
 */

@ControllerAdvice
public class ControllerAdvisor extends ResponseEntityExceptionHandler {

	@ExceptionHandler(UserCustomException.class)
	public ResponseEntity<Object> handleUserNotFoundException(UserCustomException ex, WebRequest request) {

		Map<String, Object> body = new LinkedHashMap<>();
		body.put("timestamp", LocalDateTime.now());
		body.put("message", ex.getMessage());

		return new ResponseEntity<>(body, HttpStatus.NOT_FOUND);
	}
	
	
	@ExceptionHandler(RequestException.class)
	public ResponseEntity<Object> handleRequestException(RequestException requestException,WebRequest request){
		Map<String,Object> body=new LinkedHashMap<String, Object>();
		body.put("timestamp", Instant.now());
		body.put("message", requestException.getMessage());
		return new ResponseEntity<>(body,HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(CustomException.class)
	public ResponseEntity<Object> handleCustomException(CustomException requestException,WebRequest request){
		Map<String,Object> body=new LinkedHashMap<String, Object>();
		body.put("timestamp", Instant.now());
		body.put("message", requestException.getMessage());
		return new ResponseEntity<>(body,HttpStatus.NOT_FOUND);
	}


}
