package com.dealer.demo.dealerException;

public class dealerException extends Exception {
	
private static final long serialversionUID=1L;
	
	public dealerException(String message) {
		super(message);
	}
	
	public static String NotFoundException(String id) {
		return "dealer with id = "+id+ " not found";
	}
	
	public static String dealerAlreadyExists() {
		return "dealer with Given name already exist";
	}

}
