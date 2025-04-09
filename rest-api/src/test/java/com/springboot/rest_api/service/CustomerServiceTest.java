package com.springboot.rest_api.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;

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
	
	@Test
	public void getAllEmployeesTest(){
		Customer c1 = new Customer(1,"customer1", "111111",true,new User(1,"customer1@example.com", "1234","CUSTOMER"));
		Customer c2 = new Customer(2,"customer2", "222222",true,new User(2,"customer2@example.com", "1234","CUSTOMER"));
		Customer c3 = new Customer(3,"customer3", "333333",true,new User(3,"customer3@example.com", "1234","CUSTOMER"));
		Customer c4 = new Customer(4,"customer4", "444444",false,new User(4,"customer4@example.com", "1234","CUSTOMER"));

		Pageable pageable = PageRequest.of(0, 5);	
		List<Customer> list = Arrays.asList(c1,c2,c3); 
		Page<Customer> page = new PageImpl<>(list); //fake output
		/* I am telling Spring to return this page having list of 
		 * 3 objects c1,c2,c3 whenever it encounters 
		 * customerRepository.findAll(pageable) */
		when(customerRepository.findAll(pageable)).thenReturn(page); 
		/* I do mocking because I do not want to rely on DB records 
		 * as my test case will fail if DB records were to be deleted */
		
		assertEquals(3, customerService.getAllEmployees(pageable).size());
		
		list = Arrays.asList(c1,c2,c4); 
		page = new PageImpl<>(list); //fake output
		when(customerRepository.findAll(pageable)).thenReturn(page);
		/*This 2 as expected is because I am filtering out 'inactive' customers 
		 * and c4 customer in the list is in-active. hence 2 is correct expectation */
		assertEquals(2, customerService.getAllEmployees(pageable).size());

	}
}
