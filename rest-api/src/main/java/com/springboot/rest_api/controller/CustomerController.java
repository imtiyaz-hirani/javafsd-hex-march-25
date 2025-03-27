package com.springboot.rest_api.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CustomerController {

	@GetMapping("/api/customer/hello")
	public String sayHello() {
		return "Hello from Spring boot!!!";
	}
}
