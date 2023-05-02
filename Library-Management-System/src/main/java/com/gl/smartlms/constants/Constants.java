package com.gl.smartlms.constants;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class Constants {

	public static final String ROLE_ADMIN = "Admin";
	public static final String ROLE_LIBRARIAN = "Librarian";
	
	public static final String MEMBER_FACULTY = "Faculty";
	public static final String MEMBER_STUDENT = "Student";
	public static final String MEMBER_OTHER = "Other";
	public static List<String> MEMBER_TYPES = new ArrayList<String>() {{
	    add(MEMBER_FACULTY);
	    add(MEMBER_STUDENT);
	    add(MEMBER_OTHER);
	}};
	
	
	

	
	public static final Integer BOOK_STATUS_AVAILABLE = 1;
	public static final Integer BOOK_STATUS_ISSUED = 2;
	
	public static final Integer BOOK_NOT_RETURNED = 0;
	public static final Integer BOOK_RETURNED = 1;
	
	public static final String SOMETHING_WENT_WRONG = "Something Went Wrong";
	public static final String INVALID_DATA = "Invalid data";
	public static final String INCOMPLETE_DETAILS = "Fields Are Missing";
	public static final String NO_CONTENT = "No Content Found";
	
	public static ResponseEntity<String> getResponseEntity(String responseMessage, HttpStatus httpstatus){
		
		return new ResponseEntity<String>("{\"message\" :\"" + responseMessage + "\"}",httpstatus);
	}

}
