package com.corejavaapp.main.utility.factorypattern;

public class UPI implements Payment{

	String senderAccountDetails;
	String beneficiaryCOntactNo; 
	
	
	public UPI(String senderAccountDetails, String beneficiaryCOntactNo) {
		super();
		this.senderAccountDetails = senderAccountDetails;
		this.beneficiaryCOntactNo = beneficiaryCOntactNo;
	}

	@Override
	public String[] getMandate() {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public double transactionCost() {
		// TODO Auto-generated method stub
		return 0;
	}
	
	public void UPIMethod() {
		
	}
}
