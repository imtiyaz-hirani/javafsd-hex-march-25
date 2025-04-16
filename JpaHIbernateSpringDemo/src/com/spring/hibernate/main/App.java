package com.spring.hibernate.main;

import java.util.Scanner;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.spring.hibernate.main.config.AppConfig;
import com.spring.hibernate.main.controller.ProductController;
 
public class App {
	public static void main(String[] args) {
		ApplicationContext context 
				= new AnnotationConfigApplicationContext(AppConfig.class);
		Scanner sc = new Scanner(System.in);
		while(true) {
			System.out.println("Press 1. for adding product to DB");
			System.out.println("2. Delete product by id");
			System.out.println("3. Fetch all products");
			System.out.println("0. to exit ");
			int input = sc.nextInt();
			if(input == 0 ) {
				System.out.println("Exiting, thanx.");
				break; //break while
			}
			ProductController productController = AppFactory.getProductController();
			switch(input) {
				case 1: 
					productController.addProduct(sc);
					break; 
				case 2: 
					productController.deleteProduct(sc);
					break; 
				case 3: 
					productController.getProducts();
					break;
			}	
		}
		
		sc.close();
		((AnnotationConfigApplicationContext)context).close();
	}
}
