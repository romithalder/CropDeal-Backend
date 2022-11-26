package com.BankDetails.BankDetails.BankController;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.BankDetails.BankDetails.BankException.BankException;
import com.BankDetails.BankDetails.BankModal.BankDetails;
import com.BankDetails.BankDetails.BankRepository.BankRepository;
import com.BankDetails.BankDetails.BankService.BankService;

@CrossOrigin(origins = "*")
@RestController
@EnableEurekaClient
public class BankController {
	
	@Autowired
	private BankRepository bankRepository;
	
	@Autowired
	private BankService bs;
	
	@PostMapping("/bank")
	public ResponseEntity<?> createBankAccount(@RequestBody BankDetails b)
	{
		try {
			bs.createBankInfo(b);
			return new ResponseEntity<BankDetails>(b,HttpStatus.OK);
		}  catch (BankException e) {
			return new ResponseEntity<>(e.getMessage(),HttpStatus.CONFLICT);
		}
	}
	
	
	 @GetMapping("/bank")
	    public ResponseEntity<?> getAllBankDetails()
	    {
	    	List<BankDetails> b=bs.getAllBank();
			return new ResponseEntity<>(b, b.size()>0 ? HttpStatus.OK : HttpStatus.NOT_FOUND);
	    }
	 
	 
	 @PutMapping("/bank/{accountNo}")
	    public ResponseEntity<?> updateByID(@PathVariable("accountNo") String accountNo, @RequestBody BankDetails b) {
	    	try {
				bs.updateBank(accountNo, b);
				return new ResponseEntity<>("update Bank with accountNo"+accountNo, HttpStatus.OK);
			} catch (BankException e) {
				// TODO: handle exception
				return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
			}
	    }
	 
	    @DeleteMapping("/bank/{accountNo}")
	    public ResponseEntity<?> deleteByaccountNo(@PathVariable("accountNo") String accountNo) {
	    	try {
				bs.deleteBankByAccountNo(accountNo);
				return new ResponseEntity<>("Successfully deleted with accountNo"+accountNo,HttpStatus.OK);
			} catch (BankException e) {
				return new ResponseEntity<>(e.getMessage(),HttpStatus.NOT_FOUND);
				// TODO: handle exception
			}
	    }
	    
	    @GetMapping("/bank/{id}")
		public ResponseEntity<?> getSingleBank(@PathVariable("id") String accountNo) {
			try {
				return new ResponseEntity<>(bs.getSingleAccount(accountNo),HttpStatus.OK);
				
			} catch (Exception e) {
				// TODO: handle exception
				return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
			}
		}


}
