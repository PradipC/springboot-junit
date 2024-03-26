package com.pc.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.pc.model.User;
import com.pc.service.UserService;

@RestController
public class UserController {

	@Autowired
	UserService userServiceObject;
	
	
	@PostMapping("/create")
	public ResponseEntity<User> createUser(@RequestBody User user) {
		
		
		System.out.println("coming user from client is : "+user);
		
		User createdUser = userServiceObject.createUser(user);
	
		System.out.println("createdUser is : "+createdUser);
		
		return new ResponseEntity<>(createdUser, HttpStatus.CREATED);
	}
	
	
	@GetMapping("/get")
	public ResponseEntity<List<User>> getAllUsers(){
		
		List<User>  listOfAllUsers =  userServiceObject.getAllUsers();
        if(!listOfAllUsers.isEmpty()) {
			return new ResponseEntity<>(listOfAllUsers, HttpStatus.OK);
		}
		return new ResponseEntity<>(listOfAllUsers, HttpStatus.NO_CONTENT);
	}
	
	
	
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<String> deleteUser(@PathVariable("id") Long userId) {
		
		userServiceObject.deleteUser(userId);
		
		return new ResponseEntity<>("user deleted successfully",HttpStatus.OK);

	}

	
	@PutMapping("/update/{id}")
	public ResponseEntity<User> updateUser(@PathVariable("id") Long userId  ,@RequestBody  User user) {
		
		User oldUser =userServiceObject.getUserById(userId);
		
		oldUser.setEmail(user.getEmail());
		oldUser.setFirstName(user.getFirstName());
		oldUser.setLastName(user.getLastName());
		
		User updatedUser =userServiceObject.updateUser(oldUser);

		return new ResponseEntity<>( updatedUser , HttpStatus.OK );
	}
	
	
	
	
	
	
	
	
	
	
}
