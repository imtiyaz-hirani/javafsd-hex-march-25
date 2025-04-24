package com.springboot.rest_api.controller;

import java.security.Principal;

import org.slf4j.ILoggerFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.rest_api.config.JwtUtil;
import com.springboot.rest_api.dto.TokenDto;
import com.springboot.rest_api.exception.InvalidUsernameException;
import com.springboot.rest_api.model.User;
import com.springboot.rest_api.service.AuthService;
import com.springboot.rest_api.service.MyUserService;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = {"http://localhost:5173"})
public class AuthController {

	@Autowired
	private AuthenticationManager authenticationManager;
	@Autowired
	private AuthService authService;
	@Autowired
	private MyUserService myUserService;
	@Autowired
	private JwtUtil jwtUtil;
	
	Logger logger =  LoggerFactory.getLogger("AuthController"); 
	
	@PostMapping("/signup")
	public User signUp(@RequestBody User user) throws InvalidUsernameException {
		logger.info("Sign up in progress for User " + user.getUsername()); 
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
		logger.debug("Username given " + username); 
		return myUserService.loadUserByUsername(username);
	}
	
	@PostMapping("/token/generate")
	public TokenDto generateToken(@RequestBody User user,TokenDto dto) {
		/*Step 1. Build authentication ref based on username,passord given*/
		Authentication auth = 
		new UsernamePasswordAuthenticationToken(user.getUsername(),user.getPassword());
	
		authenticationManager.authenticate(auth);
		
		/*Step 2: Generate the token since we know that credentials are correct */
		String token =  jwtUtil.generateToken(user.getUsername()); 
		dto.setToken(token);
		dto.setUsername(user.getUsername());
		dto.setExpiry(jwtUtil.extractExpiration(token).toString());
		logger.info("Token generated for User " + user.getUsername()); 
		logger.warn("Token will expiry On " + jwtUtil.extractExpiration(token).toString());
		 
		return dto; 
	}
	
	@GetMapping("/user/details")
	public UserDetails getUserDetails(Principal principal) {
		String username = principal.getName();
		return myUserService.loadUserByUsername(username);
	}
	
	/*On the basis of current password , u can let them reset the password  */
	@PostMapping("/reset")
	public void resetPassword(Principal principal, @RequestBody User user) {
		/* read username from principal and fetch User details from DB */
		
		/* then update the old password in fetched user with new password*/
		
		/*Save the new User in DB */
	}
	
	
}
