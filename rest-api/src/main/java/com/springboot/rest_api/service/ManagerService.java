package com.springboot.rest_api.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springboot.rest_api.exception.InvalidIDException;
import com.springboot.rest_api.model.Manager;
import com.springboot.rest_api.repository.ManagerRepository;

@Service
public class ManagerService {

	@Autowired
	private ManagerRepository managerRepository;
	
	public Manager add(Manager manager) {
		return managerRepository.save(manager);
	}

	public Manager getById(int managerId) throws InvalidIDException {
		Optional<Manager> optional =  managerRepository.findById(managerId);
		if(optional.isEmpty())
			throw new InvalidIDException("Manager ID invalid..");
		
		return optional.get();
	}

}
