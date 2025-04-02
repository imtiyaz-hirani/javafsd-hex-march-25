package com.springboot.rest_api.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springboot.rest_api.model.Customer;
import com.springboot.rest_api.model.CustomerProduct;
import com.springboot.rest_api.model.Product;
import com.springboot.rest_api.repository.CustomerProductRepository;

@Service
public class CustomerProductService {

	@Autowired
	private CustomerProductRepository customerProductRepository;
	
	public CustomerProduct save(CustomerProduct customerProduct) {
		return customerProductRepository.save(customerProduct);
	}

	public List<Customer> getCustomerByProductId(int pid) {
		List<CustomerProduct> list =  customerProductRepository.findByProductId(pid);
		
		return list.parallelStream()
				.map(cp->cp.getCustomer())
				.toList();
	}

	public List<Product> getProductByCustomerId(int cid) {
		List<CustomerProduct> list =  customerProductRepository.findByCustomerId(cid);

		return list.parallelStream()
				.map(cp->cp.getProduct())
				.toList();
	}

}
