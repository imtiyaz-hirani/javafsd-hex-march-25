package com.springboot.rest_api.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springboot.rest_api.model.User;
import com.springboot.rest_api.repository.AuthRepository;

@Service
public class AuthService {

	@Autowired
	private AuthRepository authRepository;
	
	public User signUp(User user) {
		if(user.getRole() == null)
			user.setRole("USER_DEFAULT");
		
		return authRepository.save(user);
	}

}
