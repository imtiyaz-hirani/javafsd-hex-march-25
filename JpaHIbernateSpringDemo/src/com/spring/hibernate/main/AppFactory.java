package com.spring.hibernate.main;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.spring.hibernate.main.config.AppConfig;
import com.spring.hibernate.main.controller.ProductController;
import com.spring.hibernate.main.repository.ProductRepository;
import com.spring.hibernate.main.service.ProductService;

public class AppFactory {

	static ApplicationContext context = 
			new AnnotationConfigApplicationContext(AppConfig.class);
	
	public static ProductController getProductController(){
		//System.out.println("controller object " +context.getBean(ProductController.class));
		return context.getBean(ProductController.class);
	}
	
	public static ProductService getProductService(){
		return context.getBean(ProductService.class);

		}
	

	public static ProductRepository getProductRepository(){
		return context.getBean(ProductRepository.class);

	}
}
