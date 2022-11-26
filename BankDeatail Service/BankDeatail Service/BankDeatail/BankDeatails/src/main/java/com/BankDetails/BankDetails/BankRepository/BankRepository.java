package com.BankDetails.BankDetails.BankRepository;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import com.BankDetails.BankDetails.BankModal.BankDetails;


@Repository
public interface BankRepository extends MongoRepository<BankDetails, String>{
	
	@Query("{ 'accountNo' : ?0}")
	Optional<BankDetails> findByAccountNo(String a);
	
	

}
