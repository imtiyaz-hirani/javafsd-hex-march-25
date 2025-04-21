package com.springboot.rest_api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.springboot.rest_api.model.C;

public interface CRepo extends JpaRepository<C, Integer>{

}
