package com.gl.smartlms.restController;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
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

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@RestController
@RequestMapping("/user")
public class UserRestController {

	@Autowired
	private UserService userService;

	
	ObjectMapper Obj = new ObjectMapper();

// ==============================================================
// User Login API
// ==============================================================

	@PostMapping(value = "/login" ,produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> signUp(@RequestParam String username, @RequestParam String password) {

		User user = userService.getUserValidate(username, password);
		try {

			if (user != null) {
				String userJson = Obj.writeValueAsString(user);

				return new ResponseEntity<String>("User Logged in Succesfully" + userJson, HttpStatus.OK);

			} else {
				return Constants.getResponseEntity(Constants.INVALID_DATA, HttpStatus.UNAUTHORIZED);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return Constants.getResponseEntity(Constants.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR);

	}
	
// ==============================================================
// User Register(Signup) API
// ==============================================================

	@PostMapping(value = "/register"  ,produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> registerUser(@Valid @RequestBody User user) {
		user = userService.registerUser(user);
		try {
			if (user != null) {

				String userJson = Obj.writeValueAsString(user);
				return new ResponseEntity<String>("User Registered Sucessfully" + userJson, HttpStatus.CREATED);
			}
		} catch (JsonProcessingException e) {

			e.printStackTrace();
		}

		return Constants.getResponseEntity(Constants.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR);
		}
}
