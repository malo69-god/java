package com.gl.smartlms.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gl.smartlms.model.User;
import com.gl.smartlms.repository.UserRepository;

@Service
public class UserServiceImpl  implements UserService{

	
	@Autowired
	private UserRepository userRepository;
	@Override
	public List<User> getAllUsers() {
		return userRepository.findAllByOrderByNameAsc();
	}
	@Override
	public User addNew(User user) {
		user.setPassword( user.getPassword());
		user.setLastModifiedDate( new Date() );
		user.setCreatedDate( user.getCreatedDate() );
		
		user.setActive(1);
		return userRepository.save(user);
	}
	@Override
	public User getUserValidate(String username, String password) {
		return userRepository.findByUsernameAndPassword(username,password);
	
	}
	@Override
	public User getByUsername(String username) {
		
		return userRepository.findByUsername(username);
	}
	@Override
	public User registerUser(User user) {
		return userRepository.save(user);
	}
	
	
	

}
