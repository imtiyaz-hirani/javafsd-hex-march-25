package com.springboot.rest_api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.rest_api.dto.StudentDto;
import com.springboot.rest_api.model.Student;
import com.springboot.rest_api.service.StudentService;

@RestController
@RequestMapping("/api/student")
@CrossOrigin(origins = {"http://localhost:5173/"})
public class StudentController {

	@Autowired
	private StudentService studentService;
	@Autowired
	private StudentDto dto;
	
	@PostMapping("/add")
	public Student add(@RequestBody Student student) {
		return studentService.add(student);
	}
	
	@GetMapping("/all")
	public StudentDto getAll(@RequestParam Integer page, @RequestParam Integer size) {
		Pageable pageable = PageRequest.of(page, size); 
		Page<Student> studentP = studentService.getAll(pageable); 
		dto.setList(studentP.getContent());
		dto.setCurrentPage(page);
		dto.setSize(size);
		dto.setTotalElements((int) studentP.getTotalElements());
		dto.setTotalPages(studentP.getTotalPages()); 
		return dto; 
	}
}
