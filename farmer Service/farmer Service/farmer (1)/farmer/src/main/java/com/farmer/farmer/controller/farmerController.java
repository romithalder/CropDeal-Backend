package com.farmer.farmer.controller;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.validation.ConstraintViolationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
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
import org.springframework.web.client.HttpClientErrorException.Conflict;
import org.springframework.web.client.RestTemplate;

import com.farmer.farmer.Repository.farmerRepo;
import com.farmer.farmer.exception.farmerInfoException;
import com.farmer.farmer.farmerService.farmerService;
import com.farmer.farmer.model.cropDetail;
import com.farmer.farmer.model.farmer;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@EnableEurekaClient
public class farmerController {
	
	@Autowired
	private farmerRepo farmerRepository;
	
	@Autowired
	private farmerService fs;
	
	@Autowired
	private RestTemplate restTemplate ;
	
	@GetMapping("/farmer")
	public ResponseEntity<?> getAllFarmer(){
		List<farmer> f=fs.getAllFarmer();
		return new ResponseEntity<>(f, f.size()>0 ? HttpStatus.OK : HttpStatus.NOT_FOUND);
	}
	
	@PostMapping("/farmer")
	public ResponseEntity<?> createFarmer(@RequestBody farmer f){
		try {
			fs.createFarmerInfo(f);
			return new ResponseEntity<farmer>(f,HttpStatus.OK);
		} catch (ConstraintViolationException e) {
			return new ResponseEntity<>(e.getMessage(),HttpStatus.UNPROCESSABLE_ENTITY);
			// TODO: handle exception
		} catch (farmerInfoException e) {
			return new ResponseEntity<>(e.getMessage(),HttpStatus.CONFLICT);
			// TODO: handle exception
		}
	}
	
	@GetMapping("/farmer/{id}")
	public ResponseEntity<?> getSinglefarmer(@PathVariable("id") String id) {
		try {
			return new ResponseEntity<>(fs.getSingleFarmer(id),HttpStatus.OK);
			
		} catch (Exception e) {
			// TODO: handle exception
			return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
		}
	}
	
	@PutMapping("/farmer/{id}")
	public ResponseEntity<?> updateByID(@PathVariable("id") String id, @RequestBody farmer f) {
		try {
			fs.updateFarmer(id, f);
			return new ResponseEntity<>("update Farmer with id"+id, HttpStatus.OK);
		} catch (farmerInfoException e) {
			// TODO: handle exception
			return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
		}
	}
	
	@DeleteMapping("/farmer/{id}")
	public ResponseEntity<?> deleteById(@PathVariable("id") String id) {
		try {
			fs.deleteFarmerById(id);
			return new ResponseEntity<>("Successfully deleted with id"+id,HttpStatus.OK);
		} catch (farmerInfoException e) {
			return new ResponseEntity<>(e.getMessage(),HttpStatus.NOT_FOUND);
			// TODO: handle exception
		}
	}
	
	@GetMapping("/farmer/crop/{id}")
	public String getFarmer(@PathVariable("id") String id) throws farmerInfoException {
	  ResponseEntity<cropDetail> c = restTemplate.exchange("http://localhost:8084/crop/"+id, HttpMethod.GET,null,cropDetail.class);
	  return restTemplate.exchange("http://localhost:8084/crop/"+id, HttpMethod.GET,null,String.class).getBody();
	}
	
	@GetMapping("/farmer/crops") 
	public List<Object> getCrops() {
		Object[] objects = restTemplate.getForObject("http://localhost:8084/crop", Object[].class);
		return Arrays.asList(objects);
	}
	
	@PostMapping("/farmer/Postcrops")
	public String createCrop(@RequestBody cropDetail c){
		return restTemplate.postForObject("http://localhost:8084/crop", c, String.class);
		        
	}
	
	@CrossOrigin(origins = "*")
	@DeleteMapping("/crop/{id}")
	public void deleteCrop(@PathVariable("id") String id) {
		 restTemplate.delete("http://localhost:8084/crop/"+id);
    }
//	 <td><button type="button" class="btn btn-primary" data-toggle="modal" data-target="#exampleModal"
//             (click)="edit(crop)">Edit</button></td>
//         <td><button type="button" class="btn btn-danger" (click)="deleteStudent(student)">Delete</button></td>
//	
	
}


