package com.gl.smartlms.service;

import java.util.List;



import org.springframework.stereotype.Service;

import com.gl.smartlms.model.User;

@Service
public interface UserService {

	public List<User> getAllUsers();

	public User addNew(User user);

	public User getUserValidate(String username, String password);

	public User getByUsername(String username);

	public  User registerUser(User user);

	

}
