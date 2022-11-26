package com.farmer.farmer.Repository;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import com.farmer.farmer.model.farmer;

@Repository
public interface farmerRepo extends MongoRepository<farmer, String> {
	
	@Query("{ 'name' : ?0}")
	Optional<farmer> findByFarmerName(String f);
	
	@Query("{ 'id' : ?0}")
	Optional<farmer> findByFarmerId(String id);
}
