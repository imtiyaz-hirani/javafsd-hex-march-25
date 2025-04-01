package com.springboot.rest_api.repository;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.springboot.rest_api.model.Product;

public interface productRepository extends JpaRepository<Product, Integer>{

	List<Product> findByCategoryId(int catId,Pageable pageable);
	List<Product> findByVendorId(int vid,Pageable pageable);
}
