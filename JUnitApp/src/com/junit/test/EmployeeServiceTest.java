package com.junit.test;

 
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.Arrays;
import java.util.List;

import org.junit.Assert;
import org.junit.jupiter.api.Test;

import com.junitapp.main.exception.InvalidInputException;
import com.junitapp.main.model.Employee;
import com.junitapp.main.service.EmployeeService;

public class EmployeeServiceTest {

	EmployeeService employeeService = new EmployeeService();
	
	@Test
	public void getAllEmployeesTest(){
		Employee e1 = new Employee(1,"harry","mumbai","IT",98000);
		Employee e2 = new Employee(2,"ronald","chennai","IT",89000);
		Employee e3 = new Employee(3,"hermione","mumbai","FIANNCE",130000);
		
		List<Employee> list = Arrays.asList(e1,e2,e3);
		/*
		 * List<Employee> expected = list; List<Employee> actual =
		 * employeeService.getAllEmployees();
		 */ 
		Assert.assertEquals(list, employeeService.getAllEmployees());
	}
	
	@Test
	public void filterListByBranchTest(){
		Employee e1 = new Employee(1,"harry","mumbai","IT",98000);
		Employee e2 = new Employee(2,"ronald","chennai","IT",89000);
		Employee e3 = new Employee(3,"hermione","mumbai","FIANNCE",130000);
		
		List<Employee> list = Arrays.asList(e1,e3);
		
	 
			InvalidInputException ex = Assert.assertThrows(InvalidInputException.class, 
					()->employeeService.filterListByBranch(list, ""));
			assertEquals("invalid branch value given".toLowerCase(), ex.getMessage());
		 
		
		try {
			Assert.assertEquals(list, employeeService.filterListByBranch(list, "mumbai") );
		} catch (InvalidInputException e) { }
		
		
	}
}
