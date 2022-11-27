package com.dealer.demo.repo;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import com.dealer.demo.model.dealer;

@Repository
public interface dealerrepo extends MongoRepository<dealer, String> {

	@Query("{ 'name' : ?0}")
	Optional<dealer> findByDealerName(String f);
}
