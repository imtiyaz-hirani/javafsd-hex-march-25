package com.springboot.rest_api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.springboot.rest_api.model.Customer;

public interface CustomerRepository 
		extends JpaRepository<Customer, Integer>{

	//save() , findAll() 
}
