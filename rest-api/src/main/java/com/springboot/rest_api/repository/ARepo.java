package com.springboot.rest_api.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.springboot.rest_api.model.A;

public interface ARepo extends JpaRepository<A, Integer>{

	List<A> findByBId(int bid);
	List<A> findByBB1(String b1);
	
}
