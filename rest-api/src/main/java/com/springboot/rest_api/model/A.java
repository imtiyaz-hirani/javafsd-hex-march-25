package com.springboot.rest_api.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name="tbl_a")
public class A {

	@Id
	@GeneratedValue(strategy =  GenerationType.IDENTITY)
	private int id; //findById() == inbuilt
	
	private String a1; //findByA1
	private String a2; //findByA2
	
	@ManyToOne //findByBId : List<A>
	private B b;//this will create a foreign key of b in A (b_id)
	
	@OneToOne //findByCId : A
	private C c; ////this will create a foreign key of c in A (c_id)
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getA1() {
		return a1;
	}

	public void setA1(String a1) {
		this.a1 = a1;
	}

	public String getA2() {
		return a2;
	}

	public void setA2(String a2) {
		this.a2 = a2;
	}

	public B getB() {
		return b;
	}

	public void setB(B b) {
		this.b = b;
	}

	public C getC() {
		return c;
	}

	public void setC(C c) {
		this.c = c;
	}
	
	
}
