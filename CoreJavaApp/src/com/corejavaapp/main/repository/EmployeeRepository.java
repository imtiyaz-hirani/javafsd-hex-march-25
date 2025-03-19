package com.corejavaapp.main.repository;

import java.util.ArrayList;
import java.util.List;

import com.corejavaapp.main.model.Employee; //ctrl + shift + O

public class EmployeeRepository {
	
	List<Employee> empList = new ArrayList<>(); 
	
	/*  to reach out to DB */
	public void populateRecords() {
		Employee e1 = new Employee(1,"harry potter", "chennai","Finance", 89000); 
		Employee e2 = new Employee(); 
		e2.setId(2);
		e2.setName("ronald weasley");
		e2.setBranch("mumbai");
		e2.setDepartment("IT");
		e2.setSalary(75000);
		
		Employee e3 = new Employee(); 
		 
		empList.add(e1);
		empList.add(e2);
		empList.add(e3);
	}
	
	public List<Employee> getEmployeeList() {
		populateRecords();
		return empList; 
	}
}
