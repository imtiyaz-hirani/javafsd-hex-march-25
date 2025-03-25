package com.main.factory;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.main.config.AppConfig;
import com.main.controller.BookController;
import com.main.repository.BookRepository;
import com.main.service.BookService;

public class BookFactory {

	static ApplicationContext context = 
			new AnnotationConfigApplicationContext(AppConfig.class);
	
	public static BookController getBookController(){
		return context.getBean(BookController.class);
	}
	
	public static BookService getBookService(){
		return context.getBean(BookService.class);

		}
	

	public static BookRepository getBookRepository(){
		return context.getBean(BookRepository.class);

	}

}
