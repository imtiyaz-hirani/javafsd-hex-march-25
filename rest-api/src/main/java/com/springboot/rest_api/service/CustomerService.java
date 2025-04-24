package com.springboot.rest_api.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.springboot.rest_api.exception.InvalidContactException;
import com.springboot.rest_api.exception.InvalidIDException;
import com.springboot.rest_api.exception.InvalidUsernameException;
import com.springboot.rest_api.model.Customer;
import com.springboot.rest_api.model.User;
import com.springboot.rest_api.repository.CustomerRepository;

@Service
public class CustomerService {

	@Autowired
	private CustomerRepository customerRepository; 
	@Autowired
	private AuthService authService;
	
	public Customer addCustomer(Customer customer) throws InvalidUsernameException {
		/* extract user from customer  */
		User user = customer.getUser();
		//set the role
		user.setRole("CUSTOMER");
		//save user in DB
		user = authService.signUp(user);
		
		//attach saved user to customer 
		customer.setUser(user);
		
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

	public void addCustomersFromExcel(MultipartFile file) throws IOException, InvalidUsernameException {
		 InputStream ins = file.getInputStream();
		 /*Convert input stream of file into reader that u can read */
		BufferedReader br = new BufferedReader(new InputStreamReader(ins));
		//System.out.println(br.lines().count());
		String line; 
		int linesToDelete = 1; 
		//ignore the first line which has headers 
		for(int i=0;i<linesToDelete;i++) {
			br.readLine();
		}
		List<Customer> list = new ArrayList<>();
		/*When there is no line to read, line becomes null and exits the loop */
		while((line = br.readLine()) != null) {
			//System.out.println(line);
			String[] fields  = line.split(",");
			
			String name = fields[1];
			String contact = fields[2];
			String username = fields[3];
			String password = fields[4];
			
			User user = new User(username,password, "CUSTOMER");
			/* save user in DB , because user needs id to be complete 
				before attaching to 
			 */
			user = authService.signUp(user);
			Customer customer = new Customer (name,contact,user); 
			list.add(customer);
		}
		/*After this loop , we have list of customers 
		 * having users attached for login*/
		
		/*Use saveAll - Batch insert to save them all in DB */
		customerRepository.saveAll(list);
	}

	public Customer getByUsername(String username) {
		 
		return customerRepository.findByUserUsername(username);
	}

}
