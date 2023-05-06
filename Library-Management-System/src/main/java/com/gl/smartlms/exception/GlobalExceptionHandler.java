package com.gl.smartlms.exception;

import java.util.HashMap;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;




//==============================================================
			// = Exception
//==============================================================
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

	@ExceptionHandler(NoSuchElementException.class)
	public String hadleImageNotFoundException(NoSuchElementException infe) {
		return infe.getMessage();
	}


	


}
