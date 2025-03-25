package com.springcore.main.util;

 import org.springframework.stereotype.Component;

@Component
public class AddressUtil {

	public String getCity(String address) { //street-name address-line city pincode country 
		String[] addr = address.split(" "); 
		return addr[2];
	}
}
