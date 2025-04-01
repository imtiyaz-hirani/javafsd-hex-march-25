package com.springboot.rest_api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.rest_api.dto.MessageResponseDto;
import com.springboot.rest_api.exception.InvalidIDException;
import com.springboot.rest_api.model.Category;
import com.springboot.rest_api.model.Product;
import com.springboot.rest_api.model.Vendor;
import com.springboot.rest_api.service.CategoryService;
import com.springboot.rest_api.service.ProductService;
import com.springboot.rest_api.service.VendorService;

@RestController
@RequestMapping("/api/product")
public class ProductController {
	
	@Autowired
	private CategoryService categoryService;
	
	@Autowired
	private VendorService vendorService;
	
	@Autowired
	private ProductService productService;
	
	@Autowired
	private MessageResponseDto dto;
	
	@PostMapping("/add/{catId}/{vid}")
	public ResponseEntity<?> addProduct(@PathVariable int catId, 
						   @PathVariable int vid,
						   @RequestBody Product product) {
		
		/* Fetch category object based on catId -- if not Throw InvalidIDException*/
		Category category = null; 
		try {
			category = categoryService.getById(catId);
		} catch (InvalidIDException e) {
			dto.setBody(e.getMessage());
			dto.setStatusCode(400);
			return ResponseEntity.status(400).body(dto);
		}
		
		/* Fetch vendor object based on vidId -- if not Throw InvalidIDException*/
		Vendor vendor = null;
		try {
			vendor = vendorService.getById(vid);
		} catch (InvalidIDException e) {
			dto.setBody(e.getMessage());
			dto.setStatusCode(400);
			return ResponseEntity.status(400).body(dto);
		}
		
		/* Attach category and vendor to product Object */
		product.setCategory(category);
		product.setVendor(vendor);
		
		/* Save product Object */
		product = productService.add(product);
		
		return ResponseEntity.ok(product);
	}

	@GetMapping("/category/{catId}")
	public ResponseEntity<?> getProductByCategory(@PathVariable int catId,
											  @RequestParam int page, 
											  @RequestParam int size) {
		
		Pageable pageable =  PageRequest.of(page, size);
		try {
			List<Product> list =  productService.getProductByCategory(catId, pageable);
			return ResponseEntity.ok(list); 
		} catch (InvalidIDException e) {
			dto.setBody(e.getMessage());
			dto.setStatusCode(400);
			return ResponseEntity.status(400).body(dto); 
		}
	}
	
	public void getProductByVendor() {
		
	}

}











