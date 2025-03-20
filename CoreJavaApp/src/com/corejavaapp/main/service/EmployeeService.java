package com.corejavaapp.main.service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Stream;

import com.corejavaapp.main.model.Employee;
import com.corejavaapp.main.model.EmployeeProject;
import com.corejavaapp.main.repository.EmployeeRepository;
import com.corejavaapp.main.utility.IdUtil;

public class EmployeeService {
	EmployeeRepository employeeRepository = new EmployeeRepository();
	
	public List<Employee> getEmployees() {
		
		return employeeRepository.getEmployeeList();
	}

	public List<Employee> filterEmployeeByBranch(List<Employee> empList,String ibranch) {
		//convert list to stream
		Stream<Employee> empStream =  empList.parallelStream(); //20 records
//		try {
//			Thread.sleep(3000);
//		} catch (InterruptedException e1) {
//			e1.printStackTrace();
//		}
		//apply the filter
		empStream = empStream
						.filter(e-> e.getBranch().equalsIgnoreCase(ibranch)); //this criteria in filter must evaluate to true or false		
		
		//recovert to List 
		empList =  empStream.toList();
		return empList;
	}

	public List<Employee> filterEmployeeByDeptartment(List<Employee> empList, String idepartment) {
		//convert list to stream + apply the filter + recovert to List 
		return empList.parallelStream()
					.filter(e->e.getDepartment().equalsIgnoreCase(idepartment))
					.toList(); 
	}

	public void addEmployee(Employee employee) {
		 employeeRepository.addEmployee(employee);
	}

	public void assignProject(int empId, int projectId) {
		int id  = new IdUtil().getRandomId(); 
		EmployeeProject employeeProject = new EmployeeProject();
		employeeProject.setId(id);
		employeeProject.setDateOfAssign(LocalDate.now());
		
		employeeRepository.assignProject(employeeProject,empId,projectId);
		
	}

}
