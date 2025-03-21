package com.corejavaapp.main.utility.factorypattern;

public class PaymentCOntroller {

	public static void main(String[] args) {
		//UPI
		Payment payment = PaymentFactory.getPaymentInstance(PaymentType.UPI);
		System.out.println(payment.transactionCost());//0
		
		//NEFT
		payment = PaymentFactory.getPaymentInstance(PaymentType.NEFT);
		System.out.println(payment.transactionCost());//100
	}
}
