package com.mydomain.springweb.userrestapi.controller;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.mydomain.springweb.userrestapi.entity.User;
import com.mydomain.springweb.userrestapi.exception.ResourceNotFoundException;
import com.mydomain.springweb.userrestapi.repository.UserRepository;

@RestController
public class UserController {
	
	// CONSTRUCTOR DI
	private final UserRepository userRepository;
	
	public UserController (UserRepository userRepository) {
		this.userRepository = userRepository;
	}
	
	
	
	// C: CREATE USERS
	@PostMapping("/add-user")
	public User addUser(@RequestBody User user) {
		return userRepository.save(user);
	}
	
	// R: GET ALL USERS
	@GetMapping("/users")
	public List<User> getAllUsers() {
		return userRepository.findAll();
	}
	
	// R: GET USER BY ID
	@GetMapping("/user/{userId}")
	public User getUserById(@PathVariable Long userId) {
		return userRepository.findById(userId)
				.orElseThrow(() -> new ResourceNotFoundException("User Not Found with ID: " + userId));
	}
	
	// U: UPDATE USER BY ID
	@PutMapping("/user/{userId}")
	public User updateUser(@RequestBody User user, @PathVariable Long userId) {
		User existedUser = userRepository.findById(userId)
		.orElseThrow(() -> new ResourceNotFoundException("User Not Found with ID: " + userId));
		
		existedUser.setFirstName(user.getFirstName());
		existedUser.setLastName(user.getLastName());
		existedUser.setEmail(user.getEmail());
		
		return userRepository.save(existedUser);
	}
	
	// D: DELETE USER BY ID
	@DeleteMapping("/user/{userId}")
	public User deleteUser(@PathVariable Long userId) {
		User existedUser = userRepository.findById(userId)
				.orElseThrow(() -> new ResourceNotFoundException("User Not Found with ID: " + userId));
		
		userRepository.delete(existedUser);
		return existedUser;
	}
}
