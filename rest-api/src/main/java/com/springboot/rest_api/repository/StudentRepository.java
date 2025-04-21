package com.springboot.rest_api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.springboot.rest_api.model.Student;

public interface StudentRepository extends JpaRepository<Student, Integer>{

}
