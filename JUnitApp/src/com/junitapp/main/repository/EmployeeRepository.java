package com.junitapp.main.repository;

import java.util.Arrays;
import java.util.List;

import com.junitapp.main.model.Employee;

public class EmployeeRepository {

	public List<Employee> getAllEmployee() {
		Employee e1 = new Employee(1,"harry","mumbai","IT",98000);
		Employee e2 = new Employee(2,"ronald","chennai","IT",89000);
		Employee e3 = new Employee(3,"hermione","mumbai","FIANNCE",130000);
		
		return Arrays.asList(e1,e2,e3);
	}
}
