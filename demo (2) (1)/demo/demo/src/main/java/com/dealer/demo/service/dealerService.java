package com.dealer.demo.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.dealer.demo.dealerException.dealerException;
import com.dealer.demo.model.dealer;

@Service
public interface dealerService {
	public dealer createDealer(dealer d) throws dealerException;
public List<dealer> getAlldealer();
	
	public dealer getSingleDealer(String id) throws dealerException;
	
	public void updateDealer(String id, dealer d) throws dealerException;
	
	public void deleteDealerById(String id) throws dealerException;
	

}
