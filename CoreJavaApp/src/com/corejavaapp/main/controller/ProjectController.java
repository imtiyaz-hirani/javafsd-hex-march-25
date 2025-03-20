package com.corejavaapp.main.controller;

import java.util.List;

import com.corejavaapp.main.model.Project;
import com.corejavaapp.main.service.ProjectService;

public class ProjectController {

	ProjectService projectService = new ProjectService();
	
	public void displayProjectRecord() {
		List<Project> list =  projectService.fetchAllProjects();
		
		for(Project p : list) {
			System.out.println(p);
		}
	}

}
