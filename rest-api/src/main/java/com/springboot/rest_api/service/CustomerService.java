package com.springboot.rest_api.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.springboot.rest_api.exception.InvalidContactException;
import com.springboot.rest_api.exception.InvalidIDException;
import com.springboot.rest_api.model.Customer;
import com.springboot.rest_api.repository.CustomerRepository;

@Service
public class CustomerService {

	@Autowired
	private CustomerRepository customerRepository; 
	
	public Customer addCustomer(Customer customer) {
		return customerRepository.save(customer);
	}

	public List<Customer> getAllEmployees(Pageable pageable) {
		return customerRepository.findAll(pageable).getContent()
						.parallelStream()
						.filter(c->c.isActive() == true)
						//.sorted((a,b)->b.getId() - a.getId())
						.toList();
	}

	public Customer getSingleCustomer(int id) throws InvalidIDException{
		Optional<Customer> optional =  customerRepository.findById(id);
		if(optional.isEmpty())
			throw new InvalidIDException("ID given is Invalid...");
		return optional.get();
	}

	public void hardDelete(Customer customer) {
		customerRepository.delete(customer);
		
	}

	public void softDelete(Customer customer) {
		customer.setActive(false);
		customerRepository.save(customer); 
		
	}

	public List<Customer> getAllCustomersByContact(String contact) throws InvalidContactException {
		if(contact.length() != 10)
			throw new InvalidContactException("contact number invalid must be 10 digits..");
		return customerRepository.findByContact(contact);
	}

	public List<Customer> getByIsActive(boolean status) { 
		return customerRepository.findByIsActive(status);
	}

	public Customer getById(int cid) {
		 
		return null;
	}

	public void deleteAllInActiveCustomers() {
		/*Fetch all customer that are inactive - List<Customer> */
		List<Customer> list= customerRepository.findByIsActive(false);
		/* Delete all these customers from the above list */
		customerRepository.deleteAll(list);
		
	}

}
