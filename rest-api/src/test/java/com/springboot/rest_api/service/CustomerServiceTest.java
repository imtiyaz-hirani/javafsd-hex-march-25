package com.springboot.rest_api.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.ErrorResponse;

import com.springboot.rest_api.exception.InvalidContactException;
import com.springboot.rest_api.model.Customer;
import com.springboot.rest_api.model.User;
import com.springboot.rest_api.repository.CustomerRepository;

@SpringBootTest
/*This tells Spring that we are going to use Mocking */
@ExtendWith(MockitoExtension.class) 

public class CustomerServiceTest {

	/* This is the service class where I want to mack my repository */
	@InjectMocks
	private CustomerService customerService;
	
	/*This is my actual repo which i am mocking*/
	@Mock
	private CustomerRepository customerRepository; 
	
	Customer c1;
	Customer c2;
	Customer c3;
	Customer c4;
	Customer c33;
	@BeforeEach
	public void init() {
		c1 = new Customer(1,"customer1", "9866523696",true,new User(1,"customer1@example.com", "1234","CUSTOMER"));
		c2 = new Customer(2,"customer2", "9866523696",true,new User(2,"customer2@example.com", "1234","CUSTOMER"));
		c3 = new Customer(3,"customer3", "333333",true,new User(3,"customer3@example.com", "1234","CUSTOMER"));
		c4 = new Customer(4,"customer4", "444444",false,new User(4,"customer4@example.com", "1234","CUSTOMER"));
		c33 = new Customer(3,"customer3", "333333",true,new User(3,"customer3@example.com", "1234","CUSTOMER"));
	}
	
	@Test
	public void getAllEmployeesTest(){
		Pageable pageable = PageRequest.of(0, 5);	
		List<Customer> list = Arrays.asList(c1,c2,c3); 
		Page<Customer> page = new PageImpl<>(list); //fake output
		/* I am telling Spring to return this page having list of 
		 * 3 objects c1,c2,c3 whenever it encounters 
		 * customerRepository.findAll(pageable) */
		when(customerRepository.findAll(pageable)).thenReturn(page); 
		/* I do mocking because I do not want to rely on DB records 
		 * as my test case will fail if DB records were to be deleted */
		
		//use case 1: count:1
		assertEquals(3, customerService.getAllEmployees(pageable).size());
		
		list = Arrays.asList(c1,c2,c4); 
		page = new PageImpl<>(list); //fake output
		when(customerRepository.findAll(pageable)).thenReturn(page);
		/*This 2 as expected is because I am filtering out 'inactive' customers 
		 * and c4 customer in the list is in-active. hence 2 is correct expectation */
		//use case 2: count: 2
		assertEquals(2, customerService.getAllEmployees(pageable).size());

		verify(customerRepository,times(2)).findAll(pageable);
	}
	
	@Test
	public void addCustomerTest() {
		when(customerRepository.save(c3)).thenReturn(c33);
		//use case 1: count - 1
		assertEquals(c33, customerService.addCustomer(c3));
		verify(customerRepository,times(1)).save(c3);
	}
	
	@Test
	public void getAllCustomersByContactTest() throws InvalidContactException {
		//expectation : List<Customer> list = Arrays.asList(c1,c2); 
		//actual: customerService.getAllCustomersByContact(9866523696)
		List<Customer> list = Arrays.asList(c1,c2);
		String contact ="9866523696";
		when(customerRepository.findByContact(contact)).thenReturn(list); 
		
		//use case 1: contact valid
		try {
			assertEquals(list, customerService.getAllCustomersByContact(contact));
		} catch (InvalidContactException e) { }
		
		//use case 2: contact invalid 
		contact = "11121212";
		try {
			assertEquals(list, customerService.getAllCustomersByContact(contact));
		} catch (InvalidContactException e) { 
			assertEquals("contact number invalid must be 10 digits..", e.getMessage());
		}
	}
	
	@Test
	public void getByIsActive() {
		
	}
}
