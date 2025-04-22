package com.springboot.rest_api.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
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
	public List<Student> getAll(Pageable pageable) {
		 
		return studentRepository.findAll(pageable).getContent();
	}

}
