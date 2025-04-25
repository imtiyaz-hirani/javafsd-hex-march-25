package com.springboot.rest_api.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springboot.rest_api.exception.InvalidIDException;
import com.springboot.rest_api.model.Warehouse;
import com.springboot.rest_api.repository.WarehouseRepository;

@Service
public class WarehouseService {

	@Autowired
	private WarehouseRepository warehouseRepository;
	
	public Warehouse save(Warehouse warehouse) { 
		return warehouseRepository.save(warehouse);
	}

	public Warehouse getById(int wid) throws InvalidIDException {
		Optional<Warehouse> optional =  warehouseRepository.findById(wid);
		if(optional.isEmpty())
			throw new InvalidIDException("warehouse Id invalid...");
		return optional.get();
	}

	public List<Warehouse> getAll() {
		 
		return warehouseRepository.findAll();
	}

}
