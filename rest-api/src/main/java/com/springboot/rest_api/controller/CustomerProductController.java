package com.springboot.rest_api.controller;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.rest_api.exception.InvalidIDException;
import com.springboot.rest_api.model.Customer;
import com.springboot.rest_api.model.CustomerProduct;
import com.springboot.rest_api.model.Product;
import com.springboot.rest_api.service.CustomerProductService;
import com.springboot.rest_api.service.CustomerService;
import com.springboot.rest_api.service.ProductService;
 
@RestController
@RequestMapping("/api/customer/product")
public class CustomerProductController {
	
	@Autowired
	private CustomerService customerService; 
	@Autowired
	private ProductService productService;
	@Autowired
	private CustomerProductService customerProductService;
	
	@PostMapping("/purchase/{cid}/{pid}")
	public CustomerProduct purchaseProduct(@PathVariable int cid , 
								@PathVariable int pid,
								@RequestBody CustomerProduct customerProduct) throws InvalidIDException {
		
		/* Fetch customer from cid, if not - throw InvalidIdException */
		Customer customer = customerService.getSingleCustomer(cid);
		
		/* Fetch product from pid, if not - throw InvalidIdException */
		Product product =  productService.getById(pid);
		
		/* Attach customer and product to customerProduct object */
		customerProduct.setCustomer(customer);
		customerProduct.setProduct(product);
		
		if(customerProduct.getDateOfPurchase() == null)
				customerProduct.setDateOfPurchase(LocalDate.now());
		
		/* Save customerProduct Object in DB */
		return customerProductService.save(customerProduct);
	}
}
