package com.springboot.rest_api;
 
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
 

@SpringBootApplication /* (exclude = SecurityAutoConfiguration.class) */
public class RestApiApplication {

	public static void main(String[] args) {
		 SpringApplication.run(RestApiApplication.class, args);
	}

	 
}
