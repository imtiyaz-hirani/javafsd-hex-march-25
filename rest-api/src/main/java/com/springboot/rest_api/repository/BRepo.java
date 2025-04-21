package com.springboot.rest_api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.springboot.rest_api.model.B;

public interface BRepo extends JpaRepository<B, Integer>{

}
