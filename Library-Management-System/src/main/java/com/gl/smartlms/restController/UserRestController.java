package com.gl.smartlms.restController;

import org.springframework.beans.factory.annotation.Autowired;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.gl.smartlms.constants.Constants;
import com.gl.smartlms.model.User;
import com.gl.smartlms.service.UserService;
import javax.validation.Valid;


import com.fasterxml.jackson.databind.ObjectMapper;

@RestController
@RequestMapping("/user")
public class UserRestController {

	@Autowired
	private UserService userService;

	//Rest Api For login
	@PostMapping(value = "/login")
	public ResponseEntity<String> signUp(@RequestParam String username, @RequestParam String password) {

		ObjectMapper Obj = new ObjectMapper();
		User user = userService.getUserValidate(username, password);
		try {
			// Converting the Java object into a JSON string
			if (user != null) {
				String userJson = Obj.writeValueAsString(user);
				// Displaying Java object into a JSON string
				return new ResponseEntity<String>(userJson, HttpStatus.OK);
			} else {
				return Constants.getResponseEntity(Constants.INVALID_DATA, HttpStatus.NOT_FOUND);

			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return Constants.getResponseEntity(Constants.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR);

	}
	
	@PostMapping(value ="/register")
	public ResponseEntity<String> registerUser(@Valid @RequestBody User user){
		user = userService.registerUser(user);
		if(user != null) {
			return new ResponseEntity<String>("User Registered Sucessfully", HttpStatus.CREATED);
		}
		return Constants.getResponseEntity(Constants.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR);

		
	}
	
	
	
	
}
