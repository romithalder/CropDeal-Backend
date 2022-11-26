package com.BankDetails.BankDetails.BankService;

import java.util.List;

import org.springframework.stereotype.Service;

import com.BankDetails.BankDetails.BankDeatailsApplication;
import com.BankDetails.BankDetails.BankException.BankException;
import com.BankDetails.BankDetails.BankModal.BankDetails;


@Service
public interface BankService {
	
public void createBankInfo(BankDetails e) throws BankException;
	
	public List<BankDetails> getAllBank();

	public BankDetails getSingleAccount(String accountHolderName) throws BankException;

	public void updateBank(String accountNo, BankDetails e) throws BankException;

	public void deleteBankByAccountNo(String accountNo) throws BankException;
	


}
