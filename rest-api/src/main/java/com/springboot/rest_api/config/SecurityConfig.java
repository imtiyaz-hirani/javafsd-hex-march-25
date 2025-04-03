package com.springboot.rest_api.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
 import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

	@Bean
	SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		http
		.csrf(csrf->csrf.disable())
			.authorizeHttpRequests((authorize) -> authorize
				.requestMatchers("/api/customer/public/hello").permitAll()
				.requestMatchers("/api/customer/private/hello").authenticated()
				.requestMatchers("/api/auth/signup").permitAll()
				.anyRequest().authenticated()
			)
			/* Activating basic Auth */
		.httpBasic(Customizer.withDefaults());
		return http.build();
	}
	
	@Bean
	UserDetailsService userDetailsService() {
		
		User user1 = (User) User.withUsername("harry")
						.password("{noop}potter")
						.roles("USER_DEFAULT")
						.build();
		
		User user2 = (User) User.withUsername("john")
						.password("{noop}doe")
						.roles("USER_DEFAULT")
						.build();
		 
		return new InMemoryUserDetailsManager(user1,user2);
	}
	
	@Bean
	BCryptPasswordEncoder passEncoder(){
		return new BCryptPasswordEncoder();
	}
	
	
}
