package com.springboot.rest_api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.springboot.rest_api.model.Vendor;

 
public interface VendorRepository extends JpaRepository<Vendor, Integer>{

	Vendor findByUserUsername(String username);

}
