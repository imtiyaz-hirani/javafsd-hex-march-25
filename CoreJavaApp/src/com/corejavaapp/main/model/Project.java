package com.corejavaapp.main.model;

public class Project {
	private int id; 
	private String title; 
	private int credits;
	
	public Project() { }

	public Project(int id, String title, int credits) {
		super();
		this.id = id;
		this.title = title;
		this.credits = credits;
	}

	public Project(String title, int credits) {
		super();
		this.title = title;
		this.credits = credits;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public int getCredits() {
		return credits;
	}

	public void setCredits(int credits) {
		this.credits = credits;
	}

	@Override
	public String toString() {
		return "Project [id=" + id + ", title=" + title + ", credits=" + credits + "]";
	} 
	
	
}
