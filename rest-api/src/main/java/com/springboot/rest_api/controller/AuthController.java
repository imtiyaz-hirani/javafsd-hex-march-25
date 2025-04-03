package com.springboot.rest_api.controller;

import org.springframework.beans.factory.annotation.Autowired;
 import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.rest_api.model.User;
import com.springboot.rest_api.service.AuthService;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

	@Autowired
	private AuthService authService;
	
	
	@PostMapping("/signup")
	public User signUp(@RequestBody User user) {
		return authService.signUp(user);
	}
}
