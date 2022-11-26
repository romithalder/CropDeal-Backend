package com.BankDetails.BankDetails.BankService;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.BankDetails.BankDetails.BankException.BankException;
import com.BankDetails.BankDetails.BankModal.BankDetails;
import com.BankDetails.BankDetails.BankRepository.BankRepository;

@Service
public class bankServiceImpli implements BankService{
	
	@Autowired
	private BankRepository bkr;

	@Override
	public void createBankInfo(BankDetails e) throws BankException {
		// TODO Auto-generated method stub
		Optional<BankDetails> BankOptional=bkr.findByAccountNo(e.getAccountNo());
		if(BankOptional.isPresent()) {
			throw new BankException(BankException.BankAlreadyExists());
		}
		else {
			bkr.save(e);
		}
		
	}

	@Override
	public List<BankDetails> getAllBank() {
		// TODO Auto-generated method stub
		List<BankDetails> bdm=bkr.findAll();
		if(bdm.size()>0) {
			return bdm;
		}
		else {
			return new ArrayList<BankDetails>();
		}
	}

	@Override
	public BankDetails getSingleAccount(String accountNo) throws BankException {
		// TODO Auto-generated method stub
		Optional<BankDetails> OptionalBank=bkr.findById(accountNo);
		if(!OptionalBank.isPresent()) {
			throw new BankException(BankException.NotFoundException(accountNo));
		}
		else {
			return OptionalBank.get();
		}
	}

	@Override
	public void updateBank(String AccountNo, BankDetails e) throws BankException {
		// TODO Auto-generated method stub
		Optional<BankDetails> optionalBank=bkr.findByAccountNo(AccountNo);
		
		if(optionalBank.isPresent()) {
			
			
			
			BankDetails BankToUpdate=optionalBank.get();
			BankToUpdate.setBankname(e.getBankname());
			BankToUpdate.setAccountNo(e.getAccountNo());
			BankToUpdate.setAccountHolderName(e.getAccountHolderName());
			BankToUpdate.setIfscCode(e.getIfscCode());
			BankToUpdate.setDebitCardNo(e.getDebitCardNo());
			BankToUpdate.setCvv(e.getCvv());
			BankToUpdate.setExpiry(e.getExpiry());
			bkr.save(BankToUpdate);
			
		}else {
			throw new BankException(BankException.NotFoundException(AccountNo));
		}
		
	}

	@Override
	public void deleteBankByAccountNo(String accountNo) throws BankException {
		// TODO Auto-generated method stub
		Optional<BankDetails> optionalBank=bkr.findByAccountNo(accountNo);
		if(!optionalBank.isPresent()) {
			throw new BankException(BankException.NotFoundException(accountNo));
		}
		else {
			bkr.deleteById(accountNo);
		}
		
	}
	
	

}
