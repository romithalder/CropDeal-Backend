package com.admin.details.admindetails.AdminException;

public class AdminException extends Exception {
	
	private static final long serialversionUID = 1L;
	public AdminException(String message)
	{
		super(message);
	}
	public static String NotFoundException(String id) {
		return "Admin with id = "+id+ " not found";
	}
	
	public static String AdminAlreadyExists() {
		return "Admin with Given name already exist";
	}



}
