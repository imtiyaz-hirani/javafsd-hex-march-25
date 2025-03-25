package com.springcore.main;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.springcore.main.config.ApplicationConfig;
import com.springcore.main.util.DBUtil;
import com.springcore.main.util.MyUtility;

public class App {
	public static void main(String[] args) {
		/*Show spring the location of you configuration class
		 * having Beans */
		
		ApplicationContext context 
				= new AnnotationConfigApplicationContext(ApplicationConfig.class);
		//first object fetch from context
		MyUtility util = context.getBean(MyUtility.class); 
		System.out.println(util);
		
		//second object fetch from context
		MyUtility util2 = context.getBean(MyUtility.class); 
		System.out.println(util2);
		
		String name= "harry potter";
		System.out.println(util.getFristName(name));
		System.out.println(util.getLastName(name));
		
		System.out.println("-------------------------------------------------");
		DBUtil dbUtil = context.getBean(DBUtil.class);
		dbUtil.dbConnect();
		((AnnotationConfigApplicationContext)context).close();
	}
}

/*
 *   A: m1 m2 m3 
 *   A a1=new A(); //100x
 *   a1.m1()
 *   a1.m2()
 *   a1 = null; --let spring do this!!! -- it can do it if A is declared as Bean 
 * 	 a1.m3() //NPE 
 * 
 * 	  @Bean -- spring vl register this object in its context(memory)
 *    A getAInstance(){ 
 *      return new A();
 *    }
 *    
 *    CONTEXT
 *    -------
 *    A a=new A()
 * */
