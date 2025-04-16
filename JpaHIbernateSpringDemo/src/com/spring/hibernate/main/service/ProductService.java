package com.spring.hibernate.main.service;

import java.util.List;

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
	
	@Transactional
	public void deleteProduct(int id) {
		//fetch the object from DB based in this id 
		Product product =  entityManager.find(Product.class, id);
		if(product == null)
			throw new IllegalArgumentException("ID is Invalid, no product found");
		entityManager.remove(product);
	}

	public List<Product> getProducts() {
		return entityManager
			.createQuery("select p from Product p", Product.class)
			.getResultList();
	}

}

/*
 * select * from product
 * select p from Product p 
 * */
