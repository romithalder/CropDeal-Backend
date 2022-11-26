package com.farmer.farmer.farmerService;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.validation.ConstraintViolationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.farmer.farmer.Repository.farmerRepo;
import com.farmer.farmer.exception.farmerInfoException;
import com.farmer.farmer.model.farmer;

@Service
public class farmerServiceImple implements farmerService {
	
	@Autowired
	private farmerRepo farmerRepository;
	
	@Override
	public farmer createFarmerInfo(farmer f) throws ConstraintViolationException, farmerInfoException {
		
		Optional<farmer> farmerOptional=farmerRepository.findByFarmerName(f.getName());
		Optional<farmer> farmerIdOptional=farmerRepository.findByFarmerId(f.getId());
		if(farmerOptional.isPresent()) {
			throw new farmerInfoException(farmerInfoException.FarmerAlreadyExists());
		}else
		if(farmerIdOptional.isPresent()) {
			throw new farmerInfoException(farmerInfoException.FarmerAlreadyExists());
		}
		else {
		 return	farmerRepository.save(f);
		}
		// TODO Auto-generated method stub

	}

	@Override
	public List<farmer> getAllFarmer() {
		// TODO Auto-generated method stub
		List<farmer> f=farmerRepository.findAll();
		if(f.size()>0) {
			return f;
		}
		else {
			return new ArrayList<farmer>();
		}
	}

	@Override
	public void updateFarmer(String id, farmer e) throws farmerInfoException {
Optional<farmer> optionalFarmer=farmerRepository.findById(id);
		
		if(optionalFarmer.isPresent()) {
			
			
			
			farmer FarmerToUpdate=optionalFarmer.get();
			FarmerToUpdate.setName(e.getName());
			FarmerToUpdate.setEmail(e.getEmail());
			FarmerToUpdate.setAddress(e.getAddress());
			FarmerToUpdate.setMobile(e.getMobile());
			FarmerToUpdate.setPassword(e.getPassword());
			farmerRepository.save(FarmerToUpdate);
			
		}else {
			throw new farmerInfoException(farmerInfoException.NotFoundException(id));
		}
		
	}

	@Override
	public void deleteFarmerById(String id) throws farmerInfoException {
		// TODO Auto-generated method stub
		Optional<farmer> optionalFarmer=farmerRepository.findById(id);
		if(!optionalFarmer.isPresent()) {
			throw new farmerInfoException(farmerInfoException.NotFoundException(id));
		}
		else {
			farmerRepository.deleteById(id);
		}
		
	}

	@Override
	public farmer getSingleFarmer(String id) throws farmerInfoException {
		// TODO Auto-generated method stub
		Optional<farmer> optionalEmployee=farmerRepository.findById(id);
		if(!optionalEmployee.isPresent()) {
			throw new farmerInfoException(farmerInfoException.NotFoundException(id));
		}
		else {
			return optionalEmployee.get();
		}
	}

}
