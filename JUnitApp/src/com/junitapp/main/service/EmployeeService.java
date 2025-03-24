package com.junitapp.main.service;

import java.util.List;

import com.junitapp.main.model.Employee;
import com.junitapp.main.repository.EmployeeRepository;

public class EmployeeService {
	
	EmployeeRepository employeeRepository = new EmployeeRepository();
	
	public List<Employee> getAllEmployees() {
		return employeeRepository.getAllEmployee();
	}
	
	public List<Employee> filterListByBranch(List<Employee> list, String branch) {
		return list.stream()
				.filter((e)->e.getBranch().equals(branch))
				.toList();
	}
	
	public List<String> getEmployeeName(List<Employee> list) {
		return  list.stream()
					.map(e->e.getName())
					.toList();
	}
}
