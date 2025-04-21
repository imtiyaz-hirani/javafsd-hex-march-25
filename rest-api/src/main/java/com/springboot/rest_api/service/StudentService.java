package com.springboot.rest_api.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springboot.rest_api.model.Student;
import com.springboot.rest_api.repository.StudentRepository;

@Service
public class StudentService {

	@Autowired
	private StudentRepository studentRepository;
	public Student add(Student student) {
		 
		return studentRepository.save(student);
	}

}
