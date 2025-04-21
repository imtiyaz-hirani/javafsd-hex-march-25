package com.springboot.rest_api.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springboot.rest_api.model.A;
import com.springboot.rest_api.model.B;
import com.springboot.rest_api.model.C;
import com.springboot.rest_api.repository.ARepo;
import com.springboot.rest_api.repository.BRepo;
import com.springboot.rest_api.repository.CRepo;

import jakarta.transaction.Transactional;
@Service
public class ABCService {

	@Autowired
	private ARepo aRepo;
	@Autowired
	private BRepo bRepo;
	@Autowired
	private CRepo cRepo; 
	
	public List<A> getAByBid(int bid) {
		 
		return aRepo.findByBId(bid);
		
	}
	
	@Transactional  //(A(debit by 50) ---> Rs.50 ---> B(credit by 50))
	public A postABC(A a) {
		// extract B and C from A. Y? because the end user is not giving u ID. 
		//plus, Without adding B and C, u cannot add A. 
		
		B b= a.getB();
		C c= a.getC();
		
		//save B and C in DB , make sure u re-attach this saved objects to A again
		//Y? B and C after save will be given ids by hibernate 
		bRepo.save(b); 
		cRepo.save(c);
		
		//attach B and C with ids to A
		a.setB(b);
		a.setC(c);
		
		//now save A in DB 
		return aRepo.save(a);
	}

}
