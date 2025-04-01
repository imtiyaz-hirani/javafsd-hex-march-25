package com.springboot.rest_api.model;

 
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "product") //this will be the name of the table 
public class Product {  
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;  //findById	
	@Column(nullable = false)
	private String title; //findByTitle
	
	@Column(nullable = false, length = 512)
	private String shortDescription;  
	
	@Column(length = 2000)
	private String description; 
	
	private String imageUrl; 
	
	private double price = 0;
	
	@ManyToOne
	private Category category; //findByCategoryId

	@ManyToOne
	private Vendor vendor; //findByVendorId
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getShortDescription() {
		return shortDescription;
	}

	public void setShortDescription(String shortDescription) {
		this.shortDescription = shortDescription;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public Vendor getVendor() {
		return vendor;
	}

	public void setVendor(Vendor vendor) {
		this.vendor = vendor;
	}  
	
	
	
}










