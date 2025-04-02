package com.springboot.rest_api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.springboot.rest_api.model.Manager;

public interface ManagerRepository extends JpaRepository<Manager, Integer>{

}
