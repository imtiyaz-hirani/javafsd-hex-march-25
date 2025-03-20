package com.corejavaapp.main.utility;

public class IdUtil {

	public int getRandomId(){
		/*Generate random primary keys */
		double random = Math.random() * 10000000; // 0.00  0.99  * 10000000
		return (int) random;
	}
}
