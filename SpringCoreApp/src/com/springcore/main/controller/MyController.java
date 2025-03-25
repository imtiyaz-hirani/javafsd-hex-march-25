package com.springcore.main.controller;

import com.springcore.main.util.AddressUtil;

public class MyController {
	
	AddressUtil addressUtil;

	public MyController(AddressUtil addressUtil) {
		super();
		this.addressUtil = addressUtil;
	}

	public String getCity(){
		
		String city = addressUtil.getCity("101,kings-lane some-address mumbai 459798 india"); 
		return city; 
	}
}
