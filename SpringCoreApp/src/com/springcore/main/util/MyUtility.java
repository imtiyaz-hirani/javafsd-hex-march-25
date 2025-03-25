package com.springcore.main.util;

public class MyUtility {

	/* spring: full name : extract first name and last name */
	
	public String getFristName(String fullName){ //fname mname lname
		String[] name = fullName.split(" "); //["fname", "mname", "lname"]
		return name[0];
	}
	
	public String getLastName(String fullName){
		String[] name = fullName.split(" "); //["fname", "mname", "lname"]
		return name[name.length-1]; //name[2]
	}
}
