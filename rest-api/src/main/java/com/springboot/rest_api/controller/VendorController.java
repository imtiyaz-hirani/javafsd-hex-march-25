package com.springboot.rest_api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.rest_api.exception.InvalidUsernameException;
import com.springboot.rest_api.model.Vendor;
import com.springboot.rest_api.service.VendorService;

@RestController
@RequestMapping("/api/vendor")
@CrossOrigin(origins = {"http://localhost:5173/"})
public class VendorController {

	@Autowired
	private VendorService vendorService;
 
	@PostMapping("/add")
	public Vendor postVendor(@RequestBody Vendor vendor) throws InvalidUsernameException {
		return vendorService.add(vendor);
	}
	
	@GetMapping("/all")
	public List<Vendor> getAllVendor() {
		return vendorService.getAll();
	}
	
}
