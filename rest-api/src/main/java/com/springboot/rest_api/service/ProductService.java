package com.springboot.rest_api.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.springboot.rest_api.exception.InvalidIDException;
import com.springboot.rest_api.model.Category;
import com.springboot.rest_api.model.Product;
import com.springboot.rest_api.repository.CategoryRepository;
import com.springboot.rest_api.repository.productRepository;

@Service
public class ProductService {

	@Autowired
	private productRepository productRepository;
	
	@Autowired
	private CategoryRepository categoryRepository; 
	
	public Product add(Product product) {
		return productRepository.save(product);
	}

	public List<Product> getProductByCategory(int catId, Pageable pageable) throws InvalidIDException {
		Optional<Category> optional = categoryRepository.findById(catId);
		if(optional.isEmpty())
			throw new InvalidIDException("Category ID Invalid.."); 
		 
		return productRepository.findByCategoryId(catId,pageable);
	}

}
