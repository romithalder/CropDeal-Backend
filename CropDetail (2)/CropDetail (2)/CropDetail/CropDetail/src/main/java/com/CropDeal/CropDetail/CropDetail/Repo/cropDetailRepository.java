package com.CropDeal.CropDetail.CropDetail.Repo;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import com.CropDeal.CropDetail.CropDetail.model.cropDetail;

@Repository
public interface cropDetailRepository extends MongoRepository<cropDetail, String> {

	@Query("{ 'type' : ?0}")
	Optional<cropDetail> findByCropName(String f);
}
