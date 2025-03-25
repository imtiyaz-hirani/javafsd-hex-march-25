package com.main.repository;

import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Component;

import com.main.model.Book;
@Component
public class BookRepository {

	public List<Book> getBooks() {
		Book b1=new Book(1,"war and peace", 450);
		Book b2=new Book(1,"all hands down", 300);
		
		List<Book> list = Arrays.asList(b1,b2);
		return list;
	}
}
