package com.spring.hibernate.main;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.spring.hibernate.main.config.AppConfig;

public class App {
	public static void main(String[] args) {
		ApplicationContext context 
				= new AnnotationConfigApplicationContext(AppConfig.class);
		
		
		((AnnotationConfigApplicationContext)context).close();
	}
}
