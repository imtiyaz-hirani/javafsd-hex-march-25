package com.corejavaapp.main.model;

public class Address {
	private int id;
	private String city; 
	private String pincode;
	
	public Address() { }

	public Address(int id, String city, String pincode) {
		this.id = id;
		this.city = city;
		this.pincode = pincode;
	}

	public Address(String city, String pincode) {
		super();
		this.city = city;
		this.pincode = pincode;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getPincode() {
		return pincode;
	}

	public void setPincode(String pincode) {
		this.pincode = pincode;
	}

	@Override
	public String toString() {
		return "Address [id=" + id + ", city=" + city + ", pincode=" + pincode + "]";
	} 

}
