package com.springboot.rest_api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.springboot.rest_api.model.User;

public interface AuthRepository extends JpaRepository<User, Integer>{

}
