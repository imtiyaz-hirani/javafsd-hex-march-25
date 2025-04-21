package com.springboot.rest_api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.rest_api.model.A;
import com.springboot.rest_api.service.ABCService;

@RestController
public class ABCController {

	@Autowired
	private ABCService abcService;
	
	@GetMapping("/api/get-a1/{bid}")
	public List<A> getAByBid(@PathVariable int bid) {
		return abcService.getAByBid(bid);
	}
	
	
	@GetMapping("/api/get-a2/{cid}")
	public A getAByCId(@PathVariable int cid) {
		return null; //abcService.getAByCId(cid);
	}
	
	
	@GetMapping("/api/get-a/{b1}")
	public void getAbyBb1() {
		
	}
	
	/* you have to read all 3 together A,B,C */
	@PostMapping("/api/add/all-abc")
	public A postABC(@RequestBody A a) { //read the outer class 
		return abcService.postABC(a);
		
	}
}
