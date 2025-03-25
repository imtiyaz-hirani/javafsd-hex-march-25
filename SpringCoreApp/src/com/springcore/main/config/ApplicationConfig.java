package com.springcore.main.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.springcore.main.util.DBUtil;
import com.springcore.main.util.MyUtility;

@Configuration
public class ApplicationConfig {
	
	@Bean
	public MyUtility getMyUtilityInstance(){
		return new MyUtility();
	}
	@Bean
	public DBUtil getDBUtilInstance() {
		return new DBUtil();
	}
}
/*
 * APPLn-CONTEXT
 * -------
 * MyUtility myutil = new MyUtility()
 */