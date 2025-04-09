package com.springboot.rest_api.model;

import java.util.Objects;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
 import jakarta.persistence.OneToOne;

@Entity
public class Customer {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;  //findById
	@Column(nullable = false)
	private String name; //findByName
	@Column(nullable = false /* , unique = true */)
	private String contact; //findByContact
	private boolean isActive = true; //findByIsActive
	
	@OneToOne
	private User user; //findByUserUsername(String username)
	
	
	public Customer() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Customer(String name, String contact, User user) {
		super();
		this.name = name;
		this.contact = contact;
		this.user = user;
	}

	
	public Customer(int id, String name, String contact, boolean isActive, User user) {
		super();
		this.id = id;
		this.name = name;
		this.contact = contact;
		this.isActive = isActive;
		this.user = user;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getContact() {
		return contact;
	}

	public void setContact(String contact) {
		this.contact = contact;
	}

	public boolean isActive() {
		return isActive;
	}

	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	@Override
	public int hashCode() {
		return Objects.hash(contact, id, isActive, name, user);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Customer other = (Customer) obj;
		return Objects.equals(contact, other.contact) && id == other.id && isActive == other.isActive
				&& Objects.equals(name, other.name) && Objects.equals(user, other.user);
	}

	
}
