package com.springboot.rest_api.controller;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.rest_api.exception.InvalidUsernameException;
import com.springboot.rest_api.model.User;
import com.springboot.rest_api.service.AuthService;
import com.springboot.rest_api.service.MyUserService;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

	@Autowired
	private AuthService authService;
	@Autowired
	private MyUserService myUserService;
	
	@PostMapping("/signup")
	public User signUp(@RequestBody User user) throws InvalidUsernameException {
		return authService.signUp(user);
	}
	
	@PostMapping("/login")
	public UserDetails login(Principal principal) {
		/* Make this login as Authenticated API 
		 * If this method is called, it means that Spring Filter alreeady
		 * has correct username/password
		 * 
		 * Can i ask spring filter to share these username and password  with me?
		 * -- yes but only username, spring filter never ever shares user password 
		 * */
		String username = principal.getName();
		return myUserService.loadUserByUsername(username);
	}
}
