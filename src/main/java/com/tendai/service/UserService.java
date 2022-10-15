package com.tendai.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tendai.model.User;
import com.tendai.repository.UserRepository;

@Service
public class UserService {

	@Autowired
	UserRepository userRepositoryObject;
	
	
	/* to create user */
	public User createUser(User user) {
		
		User createdUser =userRepositoryObject.save(user);
		
		return createdUser;
	}
	
	
	/* to get all the users */
	public List<User> getAllUsers(){
		
		List<User>  allTheUsers=userRepositoryObject.findAll();
		
		return allTheUsers;
	}
	
	
	/* get specific user based on Id */
	public User getUserById(Long userId) {
		
		
		Optional<User> optionalUser = userRepositoryObject.findById(userId);
		
		boolean isPresent = optionalUser.isPresent();
		
		if(isPresent) {
			
			User user =optionalUser.get();
			return user;
		}
		
		return null;
		
	}
	
	
	/* delete user based on Id */
	public void deleteUser(Long userId) {
		
		userRepositoryObject.deleteById(userId);
		
	}
	
	
	/* updating the user */
	public User updateUser(User user) {
		
		User updatedUser =userRepositoryObject.save(user);
		
		return updatedUser;
	}
	
	
	
	
	
	
	
	
	
	
	
	
}
