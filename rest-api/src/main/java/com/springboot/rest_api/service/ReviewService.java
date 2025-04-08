package com.springboot.rest_api.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springboot.rest_api.model.Customer;
import com.springboot.rest_api.model.Product;
import com.springboot.rest_api.model.Review;
import com.springboot.rest_api.repository.ReviewRepository;

@Service
public class ReviewService {

	@Autowired
	private ReviewRepository reviewRepository;
	@Autowired
	private CustomerProductService customerProductService;
	
	public Review add(Review review) {
		return reviewRepository.save(review);
	}

	public boolean checkIfProductBought(Customer customer, Product product) {
		List<Customer> list 
				= customerProductService.getCustomerByProductId(product.getId());
		List<Integer> custIdList = list.stream().map(c->c.getId()).toList();
		
		if(custIdList.contains(customer.getId()))
			return true;
		
		return false;
	}

}
