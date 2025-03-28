package com.springboot.rest_api.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springboot.rest_api.exception.InvalidIDException;
import com.springboot.rest_api.model.Customer;
import com.springboot.rest_api.repository.CustomerRepository;

@Service
public class CustomerService {

	@Autowired
	private CustomerRepository customerRepository; 
	
	public Customer addCustomer(Customer customer) {
		return customerRepository.save(customer);
	}

	public List<Customer> getAllEmployees() {
		return customerRepository.findAll();
	}

	public Customer getSingleCustomer(int id) throws InvalidIDException{
		Optional<Customer> optional =  customerRepository.findById(id);
		if(optional.isEmpty())
			throw new InvalidIDException("ID given is Invalid...");
		return optional.get();
	}

	public void hardDelete(Customer customer) {
		customerRepository.delete(customer);
		
	}

	public void softDelete(Customer customer) {
		customer.setActive(false);
		customerRepository.save(customer); 
		
	}

}
