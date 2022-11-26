package com.farmer.farmer.farmerService;

import java.util.List;

import javax.validation.ConstraintViolationException;


import com.farmer.farmer.exception.farmerInfoException;
import com.farmer.farmer.model.farmer;

public interface farmerService  {
	public farmer createFarmerInfo(farmer f) throws ConstraintViolationException, farmerInfoException;
public List<farmer> getAllFarmer();
	
	public farmer getSingleFarmer(String id) throws farmerInfoException;
	
	public void updateFarmer(String id,farmer e) throws farmerInfoException;
	
	public void deleteFarmerById(String id) throws farmerInfoException;
}
