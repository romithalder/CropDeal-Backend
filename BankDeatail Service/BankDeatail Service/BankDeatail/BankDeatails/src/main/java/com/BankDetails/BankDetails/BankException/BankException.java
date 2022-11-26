package com.BankDetails.BankDetails.BankException;

public class BankException extends Exception{
	
	private static final long serialversionUID = 1L;
	public BankException(String message)
	{
		super();
	}
	public static String NotFoundException(String accountHolderName) {
		return "Bank with accountHolderName = "+accountHolderName+ " not found";
	}
	
	public static String BankAlreadyExists() {
		return "Bank with Given accountHolderName already exist";
	}


}
