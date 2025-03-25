package com.main.service;

import java.util.List;

import org.springframework.stereotype.Component;

import com.main.factory.BookFactory;
import com.main.model.Book;
import com.main.repository.BookRepository;

@Component
public class BookService {

	public List<Book> getBooks() {
		 BookRepository bookRepository = BookFactory.getBookRepository();
		return bookRepository.getBooks();
	}

	
}
