package com.corejavaapp.main.utility;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.corejavaapp.main.model.Employee;

public class DBUtil {

	static DBUtil dbUtil; //static memory 
	
	static {  //only once --- DBUtil
		dbUtil = new DBUtil();//100X 
	}
	
	public static DBUtil getInstance(){
		System.out.println(dbUtil);
		return dbUtil;
	}
	
	private final String userDb="root";
	private final String dbPass="techskillsit";
	private final String url="jdbc:mysql://localhost:3306/fsd_java_march_25";
	private final String driver = "com.mysql.cj.jdbc.Driver";
	private Connection con; 
	
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
/*
 * login -- logout 
 * scheduling 
 * open -- perform - close 
 * 
 * 
 * problem 1: objects of service classes --spring - manage ur service objects 
 * problem 2: objects of repo classes --spring - manage ur repo objects 
 * problem 3: objects of util classes (DBUtil) -- Singleton pattern 
 * 
 */