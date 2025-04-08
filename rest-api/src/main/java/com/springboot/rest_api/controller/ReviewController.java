package com.springboot.rest_api.controller;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.rest_api.exception.InvalidIDException;
import com.springboot.rest_api.model.Customer;
import com.springboot.rest_api.model.Product;
import com.springboot.rest_api.model.Review;
import com.springboot.rest_api.service.CustomerService;
import com.springboot.rest_api.service.ProductService;
import com.springboot.rest_api.service.ReviewService;

@RestController
@RequestMapping("/api/review")
public class ReviewController {

	@Autowired
	private CustomerService customerService;
	@Autowired
	private ProductService productService;
	@Autowired
	private ReviewService reviewService;
	
	@PostMapping("/add/{pid}")
	public Review postReview(Principal principal,
						   @PathVariable int pid, 
						   @RequestBody Review review) throws InvalidIDException {
		
		String username = principal.getName();
		Customer customer =  customerService.getByUsername(username);
		
		Product product = productService.getById(pid);
		
		boolean isBought = reviewService.checkIfProductBought(customer,product);
		if(!isBought) {
			throw new RuntimeException("Customer has not bought the product, so review not allowed");
		}
		review.setProduct(product);
		review.setCustomer(customer);
		
		return reviewService.add(review);
	}
}



