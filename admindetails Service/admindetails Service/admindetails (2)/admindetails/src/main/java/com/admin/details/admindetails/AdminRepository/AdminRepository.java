package com.admin.details.admindetails.AdminRepository;

import com.admin.details.admindetails.adminPackage.AdminDetails;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface AdminRepository extends MongoRepository<AdminDetails,String> {
	
	@Query("{ 'name' : ?0}")
	Optional<AdminDetails> findByAdminName(String a);

}
