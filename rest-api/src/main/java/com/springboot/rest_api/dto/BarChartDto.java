package com.springboot.rest_api.dto;

import java.util.Collection;
import java.util.Set;

import org.springframework.stereotype.Component;
@Component
public class BarChartDto {
	Set<String> labels; 
	Collection<Integer> numData;
	public Set<String> getLabels() {
		return labels;
	}
	public void setLabels(Set<String> labels) {
		this.labels = labels;
	}
	public Collection<Integer> getNumData() {
		return numData;
	}
	public void setNumData(Collection<Integer> numData) {
		this.numData = numData;
	} 
	
	
}
