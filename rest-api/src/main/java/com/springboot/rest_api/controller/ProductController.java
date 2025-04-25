package com.springboot.rest_api.controller;

import java.io.IOException;
import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.springboot.rest_api.dto.MessageResponseDto;
import com.springboot.rest_api.exception.InvalidIDException;
import com.springboot.rest_api.model.Category;
import com.springboot.rest_api.model.Product;
import com.springboot.rest_api.model.Vendor;
import com.springboot.rest_api.model.Warehouse;
import com.springboot.rest_api.service.CategoryService;
import com.springboot.rest_api.service.ProductService;
import com.springboot.rest_api.service.VendorService;
import com.springboot.rest_api.service.WarehouseService;

@RestController
@RequestMapping("/api/product")
@CrossOrigin(origins = {"http://localhost:5173/"})
public class ProductController {
	
	@Autowired
	private CategoryService categoryService;
	
	@Autowired
	private VendorService vendorService;
	
	@Autowired
	private ProductService productService;
	
	@Autowired
	private WarehouseService warehouseService;
	
	@Autowired
	private MessageResponseDto dto;
	
	@PostMapping("/add/{catId}/{wid}")
	public Product addProduct(@PathVariable int catId, 
						  Principal principal,
						   @PathVariable int wid,
						   @RequestBody Product product) throws InvalidIDException {
		
		/* Fetch category object based on catId -- if not Throw InvalidIDException*/
		Category category = categoryService.getById(catId);
		
		/* Fetch vendor object based on vid -- if not Throw InvalidIDException*/
		Vendor vendor = vendorService.getByUsername(principal.getName());
		
		/* Fetch warehouse object based on wid -- if not Throw InvalidIDException*/
		Warehouse warehouse =  warehouseService.getById(wid);
		
		/* Attach category and vendor to product Object */
		product.setCategory(category);
		product.setVendor(vendor);
		product.setWarehouse(warehouse);
		
		/* Save product Object */
		product = productService.add(product);
		
		return product; 
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
	
	@PostMapping("/image/upload/{pid}")
	public Product uploadImage(@PathVariable int pid, 
							@RequestParam MultipartFile file) throws IOException, InvalidIDException {
		
		return productService.uploadImage(file,pid);
	}

}











