package com.corejavaapp.main;

import java.util.List;
import java.util.Scanner;

import com.corejavaapp.main.controller.EmployeeController;
import com.corejavaapp.main.controller.ProjectController;
 import com.corejavaapp.main.model.Project;
//Design Patterns : Singleton , Factory ()  
public class App {
	public static void main(String[] args) {
		EmployeeController employeeController = new EmployeeController();
		ProjectController projectController = new ProjectController(); 
		
		Scanner sc = new Scanner(System.in);
		while(true) {
			System.out.println("------MENU--------");
			System.out.println("1. Fetch all employees");
			System.out.println("2. Filter by branch,department");
			System.out.println("3. Add Employee with Address");
			System.out.println("4. Assign project to employee");
			System.out.println("5. Fetch project for employee");
			System.out.println("0. To Exit");
			int input = sc.nextInt();
			if(input ==0) {
				System.out.println("Exiting... ");
				break; 
			}
			switch(input) {
			case 1: 
				employeeController.displayEmployeeRecord();
				break;
			
			case 2: 
				while(true) {
					System.out.println("1. Filter by Branch");
					System.out.println("2. Filter by Department");
					System.out.println("0. To Exit");
					int op = sc.nextInt();
					if(op == 0) {
						break; 
					}
					
					switch(op) {
					case 1: 
						System.out.println("Enter branch name");
						employeeController.filterByBranch(sc.next());
						break; 
					
					case 2: 
						System.out.println("Enter department name");
						employeeController.filterByDepartment(sc.next());
						break;
					
					
					}
				}
				break;
			case 3:
				employeeController.addEmployee();
				System.out.println("Employee added with address to DB..");
				break;
			case 4:
				/* fetch all employee records to show user all ids */
				employeeController.displayEmployeeRecord();
				/* fetch all project records to show user all ids */
				projectController.displayProjectRecord();
				
				employeeController.assignProject();
				System.out.println("Project assigned to employee..");
				break; 
			case 5:
				List<Project> pList =  employeeController.getProjectsByEmployeeId();
				if(pList!=null)
					pList.stream().forEach(p-> System.out.println(p));
				break; 
			}
		}
		sc.close();
	}
}
