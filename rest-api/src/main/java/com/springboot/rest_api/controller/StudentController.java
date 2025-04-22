package com.springboot.rest_api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.rest_api.model.Student;
import com.springboot.rest_api.service.StudentService;

@RestController
@RequestMapping("/api/student")
@CrossOrigin(origins = {"http://localhost:5173/"})
public class StudentController {

	@Autowired
	private StudentService studentService;
	
	@PostMapping("/add")
	public Student add(@RequestBody Student student) {
		return studentService.add(student);
	}
	
	@GetMapping("/all")
	public List<Student> getAll(@RequestParam Integer page, @RequestParam Integer size) {
		Pageable pageable = PageRequest.of(page, size); 
		return studentService.getAll(pageable); 
	}
}
