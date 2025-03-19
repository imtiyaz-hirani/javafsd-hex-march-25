package com.corejavaapp.main;

import com.corejavaapp.main.controller.EmployeeController;

public class App {
	public static void main(String[] args) {
		EmployeeController employeeController = new EmployeeController();
		employeeController.displayEmployeeRecord();
	}
}
