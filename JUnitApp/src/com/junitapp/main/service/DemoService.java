package com.junitapp.main.service;

import java.util.List;

public class DemoService {

	public int sum(double x,double y) {
		return (int) (x+y);
	}
	/*
	 * Marks of each subject should be max 100 
	 * Marks cannot be negative 
	 * total marks should be calculated based on number of subjects given. 
	 * if numberSubjects = 4, totalMarks = 4*100 = 400 
	 * */
	public Double computePercent(List<Double> list) {
		if(list == null)
			throw new NullPointerException("List cannot be Null");
		
		if(list.size() == 0 )
			throw new RuntimeException("No Subjects given");
		double marksScored = 0; 
		double totalMarks = list.size()*100; 
		
		for(double m : list) {
			if(m>100)
				throw new RuntimeException("Marks of subject cannot be more than 100");
			if(m<0)
				throw new RuntimeException("Marks of subject cannot be negative");
			
			marksScored = marksScored + m; 
		}
		double percent = (marksScored / totalMarks) * 100; 
		return percent; 
	}
}
