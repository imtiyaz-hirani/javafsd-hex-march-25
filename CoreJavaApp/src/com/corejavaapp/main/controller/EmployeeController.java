package com.corejavaapp.main.controller;

import java.util.List;
 
import com.corejavaapp.main.model.Employee;
import com.corejavaapp.main.service.EmployeeService;

public class EmployeeController {

	EmployeeService employeeService = new EmployeeService();
	
	public void displayEmployeeRecord() {
		List<Employee> empList =  employeeService.getEmployees();
		
		for(Employee e : empList) { //[e1,e2,e3]  e=e1; e=e2; e=e3 
			System.out.println(e);
		}
	}
 
	public void filterByBranch(String ibranch) {
		List<Employee> empList =  employeeService.getEmployees();
		
		empList = employeeService.filterEmployeeByBranch(empList,ibranch);
		
		for(Employee e : empList) { //[e1,e2,e3]  e=e1; e=e2; e=e3 
			System.out.println(e);
		}
	}

	public void filterByDepartment(String idepartment) {
		List<Employee> empList =  employeeService.getEmployees();
		empList = employeeService.filterEmployeeByDeptartment(empList,idepartment);
		
		for(Employee e : empList) { //[e1,e2,e3]  e=e1; e=e2; e=e3 
			System.out.println(e);
		}
	}
}
