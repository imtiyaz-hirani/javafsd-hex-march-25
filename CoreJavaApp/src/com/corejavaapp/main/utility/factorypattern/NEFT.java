package com.corejavaapp.main.utility.factorypattern;

public class NEFT implements Payment{

	String senderAccountDetails;
	String receiverAccountDetails;
	
	public NEFT(String senderAccountDetails, String receiverAccountDetails) {
		super();
		this.senderAccountDetails = senderAccountDetails;
		this.receiverAccountDetails = receiverAccountDetails;
	}

	@Override
	public String[] getMandate() {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public double transactionCost() {
		// TODO Auto-generated method stub
		return 100;
	}
	
	public void NEFTMethod() {
		
	}
}
