package com.main;

import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.main.config.AppConfig;
import com.main.controller.BookController;
import com.main.factory.BookFactory;
import com.main.model.Book;

public class App {
	public static void main(String[] args) {
		ApplicationContext context = 
				new AnnotationConfigApplicationContext(AppConfig.class);
		
		BookController bookController = BookFactory.getBookController(); 
		List<Book> list =  bookController.getBooks();
		for(Book b : list) {
			System.out.println(b);
		}
		((AnnotationConfigApplicationContext)context).close();
		
	}
}
