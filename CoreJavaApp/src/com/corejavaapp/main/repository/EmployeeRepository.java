package com.corejavaapp.main.repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.corejavaapp.main.model.Employee; //ctrl + shift + O

public class EmployeeRepository {
	
	private String userDb="root";
	private String dbPass="techskillsit";
	private String url="jdbc:mysql://localhost:3306/fsd_java_march_25";
	private String driver = "com.mysql.cj.jdbc.Driver";
	Connection con; 
	
	List<Employee> empList = new ArrayList<>(); 
	
	public void dbConnect() {
		/*Step 1: Load the driver */
		try {
			Class.forName(driver);
			System.out.println("DRIVER LOADED!!!!");
		} catch (ClassNotFoundException e) {
			System.out.println("DRIVER LOADING FAILED!!!!");
		}
		/* Step 2: Establish connection */
		try {
			con = DriverManager.getConnection(url, userDb, dbPass);
			System.out.println("CONNECTION ESTABLISHED...");
		} catch (SQLException e) {
			System.out.println("CONNECTION iSSUE...");
		}
	}
	
	public void dbClose() {
		/* Close the connection*/
		try {
			con.close();
			System.out.println("connection closed...");
		} catch (SQLException e) {
			 System.out.println(e.getMessage());	
		}
	}
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
		dbConnect();
		
		dbClose();
		return empList; 
	}
}
