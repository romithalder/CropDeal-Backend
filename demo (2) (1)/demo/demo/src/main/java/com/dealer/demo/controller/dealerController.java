package com.dealer.demo.controller;

import java.util.Arrays;
import java.util.List;

import org.apache.el.stream.Optional;
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
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.dealer.demo.dealerException.dealerException;
import com.dealer.demo.model.dealer;
import com.dealer.demo.repo.dealerrepo;
import com.dealer.demo.service.dealerService;

@CrossOrigin(origins = "*")
@RestController
@EnableEurekaClient
public class dealerController {
	
	@Autowired
	private dealerrepo dr;
	
	@Autowired
	private RestTemplate restTemplate;
	
	@Autowired
	private dealerService ds;
	
	@GetMapping("/dealer")
	public ResponseEntity<?> getAllDealer(){
		List<dealer> d=ds.getAlldealer();
		return new ResponseEntity<>(d, d.size()>0 ? HttpStatus.OK : HttpStatus.NOT_FOUND);
	}
	@PostMapping("/dealer")
	public ResponseEntity<?> createFarmer(@RequestBody dealer d){
		try {
			ds.createDealer(d);
			return new ResponseEntity<dealer>(d,HttpStatus.OK);
		}catch (dealerException ex) {
			return new ResponseEntity<>(ex.getMessage(),HttpStatus.CONFLICT);
			// TODO: handle exception
		}
	}
	@GetMapping("/dealer/{id}")
	public ResponseEntity<?> getSinglefarmer(@PathVariable("id") String id) {
		try {
			return new ResponseEntity<>(ds.getSingleDealer(id),HttpStatus.OK);
			
		} catch (Exception e) {
			// TODO: handle exception
			return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
		}
	}
	@PutMapping("/dealer/{id}")
	public ResponseEntity<?> updateByID(@PathVariable("id") String id, @RequestBody dealer d) {
		try {
			ds.updateDealer(id, d);
			return new ResponseEntity<>("update employee with id"+id, HttpStatus.OK);
		} catch (dealerException e) {
			// TODO: handle exception
			return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
		}
	}
	@DeleteMapping("/dealer/{id}")
	public ResponseEntity<?> deleteById(@PathVariable("id") String id) {
		try {
			ds.deleteDealerById(id);
			return new ResponseEntity<>("Successfully deleted with id"+id,HttpStatus.OK);
		} catch (dealerException e) {
			return new ResponseEntity<>(e.getMessage(),HttpStatus.NOT_FOUND);
			// TODO: handle exception
		}
	}
	
	@GetMapping("/dealer/crops") 
	public List<Object> getCrops() {
		Object[] objects = restTemplate.getForObject("http://localhost:8084/crop", Object[].class);
		return Arrays.asList(objects);
	}

}
