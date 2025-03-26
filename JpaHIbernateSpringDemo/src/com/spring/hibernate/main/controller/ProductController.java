package com.spring.hibernate.main.controller;

import java.util.Scanner;

import org.springframework.stereotype.Controller;

import com.spring.hibernate.main.AppFactory;
import com.spring.hibernate.main.model.Product;
import com.spring.hibernate.main.service.ProductService;

@Controller
public class ProductController {
	
	public void addProduct(Scanner sc) {
		ProductService productService = AppFactory.getProductService();
		Product product = new Product();
		System.out.println("Enter title");
		sc.nextLine();
		product.setTitle(sc.nextLine());
		System.out.println("Enter price");
		product.setPrice(sc.nextDouble());
		productService.addProduct(product); 
	}

}
