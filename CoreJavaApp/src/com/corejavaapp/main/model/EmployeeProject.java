package com.corejavaapp.main.model;

import java.time.LocalDate;

public class EmployeeProject {
	private int id; 
	private LocalDate dateOfAssign; 
	private Employee employee;
	private Project project;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public LocalDate getDateOfAssign() {
		return dateOfAssign;
	}
	public void setDateOfAssign(LocalDate dateOfAssign) {
		this.dateOfAssign = dateOfAssign;
	}
	public Employee getEmployee() {
		return employee;
	}
	public void setEmployee(Employee employee) {
		this.employee = employee;
	}
	public Project getProject() {
		return project;
	}
	public void setProject(Project project) {
		this.project = project;
	}
	@Override
	public String toString() {
		return "EmployeeProject [id=" + id + ", dateOfAssign=" + dateOfAssign + ", employee=" + employee + ", project="
				+ project + "]";
	} 
	
	
}
