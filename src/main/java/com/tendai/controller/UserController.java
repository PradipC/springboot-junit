package com.tendai.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.tendai.model.User;
import com.tendai.service.UserService;

@RestController
public class UserController {

	@Autowired
	UserService userServiceObject;
	
	
	@PostMapping("/create")
	public User createUser(@RequestBody User user) {
		
		
		System.out.println("coming user from client is : "+user);
		
		User createdUser = userServiceObject.createUser(user);
	
		System.out.println("createdUser is : "+createdUser);
		
		return createdUser;
	}
	
	
	@GetMapping("/get")
	public List<User> getAllUsers(){
		
		List<User>  listOfAllUsers =  userServiceObject.getAllUsers();
		
		return listOfAllUsers;
	}
	
	
	
	@DeleteMapping("/delete/{id}")
	public String deleteUser(@PathVariable("id") Long userId) {
		
		userServiceObject.deleteUser(userId);
		
		return "user deleted successfully";

	}

	
	@PutMapping("/update/{id}")
	public User updateUser(@PathVariable("id") Long userId  ,@RequestBody  User user) {
		
		User oldUser =userServiceObject.getUserById(userId);
		
		oldUser.setEmail(user.getEmail());
		oldUser.setFirstName(user.getFirstName());
		oldUser.setLastName(user.getLastName());
		
		User updatedUser =userServiceObject.updateUser(oldUser);
		
		return updatedUser;
	}
	
	
	
	
	
	
	
	
	
	
}
