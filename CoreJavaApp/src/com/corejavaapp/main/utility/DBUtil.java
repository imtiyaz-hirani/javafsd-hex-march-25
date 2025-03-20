package com.corejavaapp.main.utility;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.corejavaapp.main.model.Employee;

public class DBUtil {

	private String userDb="root";
	private String dbPass="techskillsit";
	private String url="jdbc:mysql://localhost:3306/fsd_java_march_25";
	private String driver = "com.mysql.cj.jdbc.Driver";
	Connection con; 
	
	List<Employee> empList = new ArrayList<>(); 
	
	public Connection dbConnect() {
		/*Step 1: Load the driver */
		try {
			Class.forName(driver);
			//System.out.println("DRIVER LOADED!!!!");
		} catch (ClassNotFoundException e) {
			System.out.println("DRIVER LOADING FAILED!!!!");
		}
		/* Step 2: Establish connection */
		try {
			con = DriverManager.getConnection(url, userDb, dbPass);
			//System.out.println("CONNECTION ESTABLISHED...");
		} catch (SQLException e) {
			System.out.println("CONNECTION iSSUE...");
		}
		
		return con; 
	}
	
	public void dbClose() {
		/* Close the connection*/
		try {
			con.close();
			//System.out.println("connection closed...");
		} catch (SQLException e) {
			 System.out.println(e.getMessage());	
		}
	}
}
