package com.corejavaapp.main.controller;

import java.util.List;

import com.corejavaapp.main.model.Employee;
import com.corejavaapp.main.service.EmployeeService;

public class EmployeeController {

	public void displayEmployeeRecord() {
		EmployeeService employeeService = new EmployeeService();
		List<Employee> empList =  employeeService.getEmployees();
		
		for(Employee e : empList) { //[e1,e2,e3]  e=e1; e=e2; e=e3 
			System.out.println(e);
		}
	}
}
