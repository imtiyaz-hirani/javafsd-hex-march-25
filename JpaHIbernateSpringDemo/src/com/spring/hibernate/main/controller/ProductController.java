package com.spring.hibernate.main.controller;

import java.util.List;
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
		System.out.println("Product added in DB");
	}

	public void deleteProduct(Scanner sc) {
		ProductService productService = AppFactory.getProductService();
		
		System.out.println("Enter id of product");
		int id = sc.nextInt();
		try {
			productService.deleteProduct(id);
			System.out.println("Product deleted from DB");	
		}
		catch( IllegalArgumentException e) {
			System.out.println(e.getMessage());
		}
		
	}

	public void getProducts() {
		ProductService productService = AppFactory.getProductService();
		List<Product> list =  productService.getProducts();
		list.stream().forEach(System.out::println);
	}

}
