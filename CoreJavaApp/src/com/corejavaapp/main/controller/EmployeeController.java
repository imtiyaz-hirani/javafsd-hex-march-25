package com.corejavaapp.main.controller;

import java.util.List;
import java.util.Random;
import java.util.Scanner;

import com.corejavaapp.main.exception.InvalidIdException;
import com.corejavaapp.main.model.Address;
import com.corejavaapp.main.model.Employee;
import com.corejavaapp.main.model.Project;
import com.corejavaapp.main.service.EmployeeService;

public class EmployeeController {

	EmployeeService employeeService = new EmployeeService();
	Scanner sc = new Scanner(System.in);
	
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

	public void addEmployee() {
		 /*Take input from User */
		
		System.out.println("Enter name");
		String name = sc.next();
		System.out.println("Enter branch");
		String branch = sc.next();
		System.out.println("Enter department");
		String department = sc.next();
		System.out.println("Enter salary");
		double salary = sc.nextDouble();
		System.out.println("Enter city");
		String city = sc.next();
		System.out.println("Enter pincode");
		String pincode = sc.next();
		
		 /*Attach these values to objects of employee and address */
		Employee employee = new Employee(name,branch,department,salary);
		
		Address address = new Address(city,pincode);
		
		/*Generate random primary keys */
		double random = Math.random() * 10000000; // 0.00  0.99  * 10000000
		int addressId = (int) random; 
		
		random = Math.random() * 10000000; 
		int empId = (int) random; 
		
		/* set the key in objects*/
		address.setId(addressId);
		employee.setId(empId);
		
		//connect address to employee 
		employee.setAddress(address);
	
		employeeService.addEmployee(employee); 
		
	}

	public void assignProject() {
		//read ids from the user 
		 System.out.println("Enter employee id");
		 int empId = sc.nextInt();
		 System.out.println("Enter project id");
		 int projectId = sc.nextInt();
		 
		 employeeService.assignProject(empId,projectId);
	}

	public List<Project> getProjectsByEmployeeId(){
		System.out.println("Enter employee id");
		int eid = sc.nextInt();
		try {
			Employee employee =   employeeService.getEmployeeById(eid);
			return employeeService.getProjectsByEmployeeId(employee.getId());
		} catch (InvalidIdException e) {
			 System.out.println(e.getMessage());
			 return null; 
		} 
		
		
	}
}















