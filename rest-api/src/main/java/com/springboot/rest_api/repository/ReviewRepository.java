package com.springboot.rest_api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.springboot.rest_api.model.Review;

public interface ReviewRepository extends JpaRepository<Review, Integer>{

}
