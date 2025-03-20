package com.corejavaapp.main.service;

import java.util.List;

import com.corejavaapp.main.model.Project;
import com.corejavaapp.main.repository.ProjectRepository;

public class ProjectService {

	ProjectRepository projectRepository = new ProjectRepository();
	
	public List<Project> fetchAllProjects() {
		return projectRepository.fetchAllProjects();
	}

}
