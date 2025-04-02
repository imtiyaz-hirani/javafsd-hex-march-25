package com.springboot.rest_api.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springboot.rest_api.model.CustomerProduct;
import com.springboot.rest_api.repository.CustomerProductRepository;

@Service
public class CustomerProductService {

	@Autowired
	private CustomerProductRepository customerProductRepository;
	
	public CustomerProduct save(CustomerProduct customerProduct) {
		return customerProductRepository.save(customerProduct);
	}

}
