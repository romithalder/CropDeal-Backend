package com.farmer.farmer.exception;

public class farmerInfoException extends Exception {
	
	
	private static final long serialversionUID=1L;
	
	public farmerInfoException(String message) {
		super(message);
	}
	
	public static String NotFoundException(String id) {
		return "Farmer with id = "+id+ " not found";
	}
	
	public static String FarmerAlreadyExists() {
		return "Farmer with Given name already exist";
	}
	public static String FarmerIdAlreadyExists() {
		return "Farmer with the same Id already exist";
	}

}
