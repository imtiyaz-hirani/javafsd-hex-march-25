package com.springboot.rest_api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.springboot.rest_api.model.Warehouse;

public interface WarehouseRepository extends JpaRepository<Warehouse, Integer>{

}
