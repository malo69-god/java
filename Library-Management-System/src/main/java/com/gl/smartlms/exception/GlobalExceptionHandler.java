package com.gl.smartlms.exception;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {
	
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public Map<String, String> handleInvalidArgument(MethodArgumentNotValidException error) {
		
		Map<String, String> errorMap = new HashMap<String, String>();
		
		error.getBindingResult().getFieldErrors().forEach(err -> {
			errorMap.put(err.getField(), err.getDefaultMessage());
		});
		
		return errorMap;
	}
	
	

}

//@RestControllerAdvice
//
//public class GlobalExceptionHandler extends ResponseEntityExceptionHandler{
//	
//@ExceptionHandler(MethodArgumentNotValidException.class)	
//public Map<String,String> handleArguementException(MethodArgumentNotValidException ex){
//	
//	Map<String,String> errors = new HashMap<>();
//	ex.getBindingResult().getFieldErrors().forEach(error ->  errors.put(error.getField(), error.getDefaultMessage()));
//	return errors;
//}
//	
//
//}
