package com.springboot.rest_api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.rest_api.dto.MessageResponseDto;
import com.springboot.rest_api.exception.InvalidContactException;
import com.springboot.rest_api.exception.InvalidIDException;
import com.springboot.rest_api.model.Customer;
import com.springboot.rest_api.service.CustomerService;

 
@RestController
public class CustomerController {

	@Autowired
	private CustomerService customerService;  //Dependency Injection DI 
	@Autowired
	private MessageResponseDto messageDto;
	
	@GetMapping("/api/customer/hello")
	public String sayHello() {
		return "Hello from Spring boot!!!";
	}
	
	@PostMapping("/api/customer/add")
	public Customer addCustomer(@RequestBody Customer customer) {
		return customerService.addCustomer(customer);
	}
	
	@GetMapping("/api/customer/getall")
	public List<Customer> getAllEmployees(@RequestParam int page, 
										  @RequestParam int size) {
		
		Pageable pageable =  PageRequest.of(page, size+1); 
		return customerService.getAllEmployees(pageable);
	}
	
	@GetMapping("/api/customer/one/{id}") //api/customer/one/5
	public ResponseEntity<?> getSingleCustomer(@PathVariable int id) {
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
	
	/*
	 * Delete: Soft delete, hard delete 
	 * */
	@DeleteMapping("/api/customer/hard-delete/{id}")
	public ResponseEntity<?> hardDeleteCustomer(@PathVariable int id) {
		try {
			//lets validate id and if valid fetch customer object
			Customer customer = customerService.getSingleCustomer(id);
			//since it is valid here at line 63, lets delete this record 
			customerService.hardDelete(customer);
			messageDto.setBody("Customer record hard deleted from DB!!");
			messageDto.setStatusCode(200);
			return ResponseEntity.ok(messageDto);
		} catch (InvalidIDException e) {
			messageDto.setBody(e.getMessage());
			messageDto.setStatusCode(400);
			return ResponseEntity.status(400).body(messageDto); 
		}
	}
	
	@DeleteMapping("/api/customer/soft-delete/{id}")
	public ResponseEntity<?> softDeleteCustomer(@PathVariable int id) {
		try {
			//lets validate id and if valid fetch customer object
			Customer customer = customerService.getSingleCustomer(id);
			//since it is valid here at line 80, lets changeisActive to false
			customerService.softDelete(customer);
			messageDto.setBody("Customer record soft deleted from DB!!");
			messageDto.setStatusCode(200);
			return ResponseEntity.ok(messageDto);
		} catch (InvalidIDException e) {
			messageDto.setBody(e.getMessage());
			messageDto.setStatusCode(400);
			return ResponseEntity.status(400).body(messageDto); 
		}
	}
	
	/*
	 * Update Customer 
	 * */
	@PutMapping("/api/customer/update/{id}")
	public ResponseEntity<?> updateCustomer(@PathVariable int id , 
								@RequestBody Customer newValue) {
		//verify the id given and fetch existing record
		try {
			Customer customer = customerService.getSingleCustomer(id);
			if(newValue.getName() != null)
				customer.setName(newValue.getName());
			if(newValue.getContact() != null)
				customer.setContact(newValue.getContact()); 
			customer = customerService.addCustomer(customer);
			return ResponseEntity.ok(customer); 
			
		} catch (InvalidIDException e) {
			messageDto.setBody(e.getMessage());
			messageDto.setStatusCode(400);
			return ResponseEntity.status(400).body(messageDto); 
		}
	}
	
	
	/*
	 * Find all customers by contact 
	 * */
	@GetMapping("/api/customer/contact")
	public ResponseEntity<?> getAllCustomersByContact(@RequestParam String contact) {
		try {
			List<Customer> list =  customerService.getAllCustomersByContact(contact);
			return ResponseEntity.ok(list); 
		} catch (InvalidContactException e) {
			messageDto.setBody(e.getMessage());
			messageDto.setStatusCode(400);
			return ResponseEntity.status(400).body(messageDto);
		}
	}
	
	@GetMapping("/api/customer/isactive")
	public List<Customer> getByIsActive(@RequestParam boolean status) {
		return customerService.getByIsActive(status); 
	}
}















