package com.springboot.rest_api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.springboot.rest_api.model.Category;

public interface CategoryRepository extends JpaRepository<Category, Integer>{

}
