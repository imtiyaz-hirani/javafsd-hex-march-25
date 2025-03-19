package com.corejavaapp.main.service;

import java.util.List;

import com.corejavaapp.main.model.Employee;
import com.corejavaapp.main.repository.EmployeeRepository;

public class EmployeeService {

	public List<Employee> getEmployees() {
		EmployeeRepository employeeRepository = new EmployeeRepository();
		return employeeRepository.getEmployeeList();
	}

}
