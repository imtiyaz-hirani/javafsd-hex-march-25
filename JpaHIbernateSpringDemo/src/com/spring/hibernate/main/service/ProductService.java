package com.spring.hibernate.main.service;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.spring.hibernate.main.model.Product;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;

@Service
public class ProductService {

	@PersistenceContext
	private EntityManager entityManager; 
	
	@Transactional
	public void addProduct(Product product) {
		int id = (int)(Math.random()*1000000);
		product.setId(id);
		entityManager.persist(product);
	}

}
