package com.gl.smartlms.exception;



import java.util.HashMap;



import java.util.*;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.gl.smartlms.customexception.*;





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

//	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
//	@ExceptionHandler(NoSuchIssueIdFoundException.class)
//	public Map<String, String> handleNosuchIssueIdFoundException(NoSuchIssueIdFoundException error){
//		
//		Map<String, String> errorMap = new HashMap<String,String>();
//		errorMap.put("message", error.getMessage());
//		return errorMap;
//	}
	




}
