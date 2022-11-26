package com.CropDeal.CropDetail.CropDetail.Exception;

public class CropDetailException extends Exception {

private static final long serialversionUID=1L;
	
	public CropDetailException(String message) {
		super(message);
	}
	
	public static String NotFoundException(String id) {
		return "Crop with id = "+id+ " not found";
	}
	
	public static String cropAlreadyExists() {
		return "Crop with Given Type already exist";
	}
}
