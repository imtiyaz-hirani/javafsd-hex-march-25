package com.springboot.rest_api.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.springboot.rest_api.repository.AuthRepository;
@Service
public class MyUserService implements UserDetailsService{

	@Autowired
	private AuthRepository authRepository;
	/*
	 * As soon as my class MyUserService implement UserDetailsService, 
	 * it becomes UserDetailsService as per inheritance is-a relation rule
	 * 
	 * ex. 
	 * class B implements A{} 
	 * B is-a A 
	 * 
	 * class dog implements Animal{}
	 * Dog is-a Animal 
	 * 
	 * class MyUserService implements UserDetailsService
	 * MyUserService is-a UserDetailsService
	 * 
	 * and do not that secutiry config needs UserDetailsService
	 */
	

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		return authRepository.findByUsername(username);
	}
}
