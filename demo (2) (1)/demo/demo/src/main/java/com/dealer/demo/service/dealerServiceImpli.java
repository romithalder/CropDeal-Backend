package com.dealer.demo.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dealer.demo.dealerException.dealerException;
import com.dealer.demo.model.dealer;
import com.dealer.demo.repo.dealerrepo;

@Service
public class dealerServiceImpli implements dealerService {
	
	@Autowired
	private dealerrepo dealerRepository;

	@Override
	public List<dealer> getAlldealer() {
		
		// TODO Auto-generated method stub
		List<dealer> d=dealerRepository.findAll();
		if(d.size()>0) {
			return d;
		}
		else {
			return new ArrayList<dealer>();
		}

	
	}

	@Override
	public dealer getSingleDealer(String id) throws dealerException {
		// TODO Auto-generated method stub
		Optional<dealer> optionaldealer=dealerRepository.findById(id);
		if(!optionaldealer.isPresent()) {
			throw new dealerException(dealerException.NotFoundException(id));
		}
		else {
			return optionaldealer.get();
		}
	}

	@Override
	public void updateDealer(String id, dealer d) throws dealerException {
		// TODO Auto-generated method stub
Optional<dealer> optionaldealer=dealerRepository.findById(id);
		
		if(optionaldealer.isPresent()) {
			
			
			
			dealer dealerToUpdate=optionaldealer.get();
			dealerToUpdate.setName(d.getName());
			dealerToUpdate.setEmail(d.getEmail());
			dealerToUpdate.setAddress(d.getAddress());
			dealerToUpdate.setMobile(d.getMobile());
			dealerToUpdate.setPassword(d.getPassword());
			dealerRepository.save(dealerToUpdate);
			
		}else {
			throw new dealerException(dealerException.NotFoundException(id));
		}

	}

	@Override
	public void deleteDealerById(String id) throws dealerException {
		// TODO Auto-generated method stub
		Optional<dealer> optionaldealer=dealerRepository.findById(id);
		if(!optionaldealer.isPresent()) {
			throw new dealerException(dealerException.NotFoundException(id));
		}
		else {
			dealerRepository.deleteById(id);
		}

	}

	@Override
	public dealer createDealer(dealer d) throws dealerException {
		// TODO Auto-generated method stub
		Optional<dealer> dealerOptional=dealerRepository.findByDealerName(d.getName());
		if(dealerOptional.isPresent()) {
			throw new dealerException(dealerException.dealerAlreadyExists());
		}
		else {
			return dealerRepository.save(d);
		}
		
		
	}

}
