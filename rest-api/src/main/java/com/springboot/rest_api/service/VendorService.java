package com.springboot.rest_api.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springboot.rest_api.exception.InvalidIDException;
import com.springboot.rest_api.exception.InvalidUsernameException;
import com.springboot.rest_api.model.User;
import com.springboot.rest_api.model.Vendor;
import com.springboot.rest_api.repository.VendorRepository;

@Service
public class VendorService {

	@Autowired
	private VendorRepository vendorRepository;
	@Autowired
	private AuthService authService;
	
	public Vendor add(Vendor vendor) throws InvalidUsernameException {
		//fetch user details and save it in DB 
		User user = vendor.getUser();
		//set user the role of VENDOR
		user.setRole("VENDOR");
		
		user  = authService.signUp(user);
		
		//attach user back to vendor 
		vendor.setUser(user);
		
		return vendorRepository.save(vendor);
	}
 
	public List<Vendor> getAll() {
		return vendorRepository.findAll();
	}

	public Vendor getById(int vid) throws InvalidIDException {
		Optional<Vendor> optional =  vendorRepository.findById(vid);
		if(optional.isEmpty())
			throw new InvalidIDException("Vendor ID Invalid..");
		
		return optional.get();
	}

	public Vendor getByUsername(String username) {
		 
		return vendorRepository.findByUserUsername(username);
	}
}
