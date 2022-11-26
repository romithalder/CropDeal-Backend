package com.CropDeal.CropDetail.CropDetail.controller;

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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.CropDeal.CropDetail.CropDetail.Exception.CropDetailException;
import com.CropDeal.CropDetail.CropDetail.Repo.cropDetailRepository;
import com.CropDeal.CropDetail.CropDetail.Service.CropDetailService;
import com.CropDeal.CropDetail.CropDetail.model.cropDetail;

@CrossOrigin(origins = "*")
@RestController
@EnableEurekaClient
public class cropController {
	
	@Autowired
	private cropDetailRepository croprepo;
	
	@Autowired
	private CropDetailService cds;
	
	@GetMapping("/crop")
	public ResponseEntity<?> getAllCrop(){
		List<cropDetail> f=cds.getAllCropDetail();
		return new ResponseEntity<>(f, f.size()>0 ? HttpStatus.OK : HttpStatus.NOT_FOUND);
	}
	
	@PostMapping("/crop")
	public ResponseEntity<?> createFarmer(@RequestBody cropDetail f){
		try {
			cds.createCropDetail(f);
			return new ResponseEntity<cropDetail>(f,HttpStatus.OK);
			// TODO: handle exception
		} catch (CropDetailException e) {
			return new ResponseEntity<>(e.getMessage(),HttpStatus.CONFLICT);
			// TODO: handle exception
		}
	}
	
	@GetMapping("/crop/{id}")
	public ResponseEntity<?> getSinglecrop(@PathVariable("id") String id) {
		try {
			return new ResponseEntity<>(cds.getSingleCropDetail(id),HttpStatus.OK);
			
		} catch (Exception e) {
			// TODO: handle exception
			return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
		}
	}
	
	@PutMapping("/crop/{id}")
	public ResponseEntity<?> updateByID(@PathVariable("id") String id, @RequestBody cropDetail f) {
		try {
			cds.updateCropDetail(id, f);
			return new ResponseEntity<>("update crop with id"+id, HttpStatus.OK);
		} catch (CropDetailException e) {
			// TODO: handle exception
			return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
		}
	}
	
	@DeleteMapping("/crop/{id}")
	public ResponseEntity<?> deleteById(@PathVariable("id") String id) {
		try {
			cds.deleteCropDetailById(id);
			return new ResponseEntity<>("Successfully deleted with id"+id,HttpStatus.OK);
		} catch (CropDetailException e) {
			return new ResponseEntity<>(e.getMessage(),HttpStatus.NOT_FOUND);
			// TODO: handle exception
		}
	}
}
