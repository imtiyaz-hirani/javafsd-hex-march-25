package com.corejavaapp.main.utility.factorypattern;

public class PaymentFactory { //factory of objects

	public static Payment getPaymentInstance(PaymentType type){
		
		switch(type) {
			
			case PaymentType.UPI:
				return new UPI("345345", "9877645443");
			case PaymentType.NEFT:
				return new NEFT("345345", "3453465");
			default: 
				return null; 
				 
		}
	}
}
