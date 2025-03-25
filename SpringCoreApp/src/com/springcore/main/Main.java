package com.springcore.main;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.springcore.main.config.AppConfig;
import com.springcore.main.controller.MyController;
import com.springcore.main.util.AddressUtil;
 
public class Main {
	public static void main(String[] args) {
		/*Spring, i am telling you to load AppConfig that has component scan */
		ApplicationContext context = 
				new AnnotationConfigApplicationContext(AppConfig.class);
		
		MyController myController = new MyController(context.getBean(AddressUtil.class));
		System.out.println(myController.getCity());
		
		((AnnotationConfigApplicationContext)context).close();
	}
}
