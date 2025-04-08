package com.springboot.rest_api.model;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "user_info")
/* convert this User into UserDetails , so that spring gets extra details about user login*/
public class User implements UserDetails{
	
	private static final long serialVersionUID = 5121238707792419121L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id; 
	
	@Column(nullable = false)
	private String username; //findByUsername
	
	@Column(nullable = false)
	private String password; //findByPassword
	
	private String role;
 
	
	public User() { //JPA needs this
		super();
		// TODO Auto-generated constructor stub
	}

	public User(String username, String password, String role) {
		super();
		this.username = username;
		this.password = password;
		this.role = role;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		/*convert role into authority using SimpleGrantedAuthority class */
		SimpleGrantedAuthority sga = new SimpleGrantedAuthority(role);
		
		/*prepare the list of GrantedAuthority and add your ur authority to it*/
		Collection<GrantedAuthority> list = new ArrayList<>();
		list.add(sga); //my authority from role. 
		
		return list;
	} 
	
	
}



