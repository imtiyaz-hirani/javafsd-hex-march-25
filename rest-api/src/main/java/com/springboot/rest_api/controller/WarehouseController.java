package com.springboot.rest_api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.rest_api.exception.InvalidIDException;
import com.springboot.rest_api.model.Manager;
import com.springboot.rest_api.model.Warehouse;
import com.springboot.rest_api.service.ManagerService;
import com.springboot.rest_api.service.WarehouseService;

@RestController
@RequestMapping("/api/warehouse")
public class WarehouseController {

	@Autowired
	private WarehouseService warehouseService;
	@Autowired
	private ManagerService managerService;
	
	@PostMapping("/add/{managerId}")
	public Warehouse add(@PathVariable int managerId, 
					@RequestBody Warehouse warehouse) throws InvalidIDException {
		
		/*Get Manager by Id if not throw InvalidIdException*/
		Manager manager = managerService.getById(managerId);
		
		/*Attach manager to warehouse Object */
		warehouse.setManager(manager);
		
		/*Save warehouse object */
		return warehouseService.save(warehouse);
	}
}
