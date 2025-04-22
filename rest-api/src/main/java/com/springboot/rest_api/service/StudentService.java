package com.springboot.rest_api.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
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
	public Page<Student> getAll(Pageable pageable) {
		 
		return studentRepository.findAll(pageable) ;
	}
	public void delete(int sid) {
		studentRepository.deleteById(sid);
		
	}

}
