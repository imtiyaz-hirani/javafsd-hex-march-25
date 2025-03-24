package com.junit.test;

import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.Arrays;
import java.util.List;

import org.junit.Assert;
import org.junit.jupiter.api.Test;

import com.junitapp.main.service.DemoService;

public class DemoServiceTest {
	
	DemoService demoService = new DemoService();
	
	@Test
	public void sum() {
		//null value check 
		assertNotNull(demoService);
		//use case 1: 2 int values 
		Assert.assertEquals(30, demoService.sum(10,20));
		//use case 2: 2 float values 
		Assert.assertEquals(30, demoService.sum(10F,20F));
		//use case 3: mixed data type
		Assert.assertEquals(30, demoService.sum(10,20F));
		//use case 4: negative values
		Assert.assertEquals(10, demoService.sum(-10,20F));
		//use case 5: assert not equals
		Assert.assertNotEquals(30, demoService.sum(-10,20F));
	}
	
	@Test
	public void computePercentTest() {
		/*Functional use cases */
		//Use case 1: give 3 marks and check the percent
		/*List<Double> list = new ArrayList<>();
		list.add(87d);
		list.add(76.0);
		list.add(68D);
		*/
		List<Double> list = Arrays.asList(87d,76.0,68D); 
		Double expected = 77.0; 
		Assert.assertEquals( expected ,demoService.computePercent(list));
		
		//Use case: list given is null 
		NullPointerException npe = 
				assertThrows(NullPointerException.class, ()->demoService.computePercent(null));
		
		assertEquals("list cannot be null".toLowerCase(), npe.getMessage().toLowerCase());
		
		//use case : list is empty 
		List<Double> listEmpty = Arrays.asList(); 
		RuntimeException re = 
				assertThrows(RuntimeException.class, ()-> demoService.computePercent(listEmpty));
		assertEquals("No Subjects given".toLowerCase(), re.getMessage().toLowerCase());
		
		List<Double> list2 = Arrays.asList(87d,102.0,68D); 
		re = assertThrows(RuntimeException.class, ()-> demoService.computePercent(list2));
		assertEquals("Marks of subject cannot be more than 100".toLowerCase(), re.getMessage().toLowerCase());
		
		List<Double> list3 = Arrays.asList(87d,76.0,-5D); 
		re = assertThrows(RuntimeException.class, ()-> demoService.computePercent(list3));
		assertEquals("Marks of subject cannot be negative".toLowerCase(), re.getMessage().toLowerCase());
		
	}
}

/* Number : Integer / Float / Double /Long  */
