package com.genesis.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.genesis.model.UserLogin;
import com.genesis.service.UserLoginService;

@CrossOrigin
@RestController
@RequestMapping(value="/genesys")
public class UserLoginAPI {
	
	@Autowired
	private UserLoginService userLoginService;
	
	@PostMapping(value="/newUser")
	public ResponseEntity<String> addUserLogin(@RequestBody UserLogin userLogin) throws Exception{
		/*
		 * This method adds a new user log in profile
		 */
		
		try {
			userLoginService.addUserLogin(userLogin);
			String successMessage = "User login profile successfully created";
			ResponseEntity<String> response = new ResponseEntity<String>(successMessage, HttpStatus.OK);
			
			return response;
			
		}catch(Exception e) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage(),e);
		}
	}
	
	@GetMapping(value="/userLogin/{userLogin}")
	public ResponseEntity<String> authenticateUserLogin(@RequestBody UserLogin userLogin, @PathVariable UserLogin login) throws Exception{
		/*
		 * This method authenticates a user login credentials
		 */
		
		try {
			userLoginService.authenticateUser(userLogin);
			String sucessMessage = "User login successful";
			ResponseEntity<String> response = new ResponseEntity<String>(sucessMessage,HttpStatus.OK);
			
			return response;
		} catch (Exception e) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage(), e);
		}
		
	}

}
