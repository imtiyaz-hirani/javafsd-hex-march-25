package com.springboot.rest_api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
 import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.rest_api.dto.MessageResponseDto;
import com.springboot.rest_api.exception.InvalidIDException;
import com.springboot.rest_api.model.Customer;
import com.springboot.rest_api.service.CustomerService;

 
@RestController
public class CustomerController {

	@Autowired
	private CustomerService customerService;
	
	@GetMapping("/api/customer/hello")
	public String sayHello() {
		return "Hello from Spring boot!!!";
	}
	
	@PostMapping("/api/customer/add")
	public Customer addCustomer(@RequestBody Customer customer) {
		return customerService.addCustomer(customer);
	}
	
	@GetMapping("/api/customer/getall")
	public List<Customer> getAllEmployees() {
		return customerService.getAllEmployees();
	}
	
	@GetMapping("/api/customer/one/{id}") //api/customer/one/5
	public ResponseEntity<?> getSingleCustomer(@PathVariable int id, 
												MessageResponseDto messageDto) {
		try {
			Customer customer = customerService.getSingleCustomer(id);
			return ResponseEntity.ok(customer); 
		}
		catch(InvalidIDException e) {
			messageDto.setBody(e.getMessage());
			messageDto.setStatusCode(400);
			return ResponseEntity.status(400).body(messageDto); 
		}
		 
	}
}
