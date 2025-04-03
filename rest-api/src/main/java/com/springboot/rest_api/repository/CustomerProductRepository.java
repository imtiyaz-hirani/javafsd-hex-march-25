package com.springboot.rest_api.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.springboot.rest_api.model.CustomerProduct;

public interface CustomerProductRepository extends JpaRepository<CustomerProduct, Integer>{
	List<CustomerProduct> findByCustomerId(int cid); 
	List<CustomerProduct> findByProductId(int pid); 
}
