package com.admin.details.admindetails.AdminController;

import com.admin.details.admindetails.AdminException.AdminException;
import com.admin.details.admindetails.AdminRepository.AdminRepository;
import com.admin.details.admindetails.AdminService.AdminService;
import com.admin.details.admindetails.adminPackage.AdminDetails;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

import javax.validation.ConstraintViolationException;

@CrossOrigin(origins = "*")
@RestController
@EnableEurekaClient
public class AdminController {
    @Autowired
    private AdminRepository adminRepository;
    
    @Autowired
	private AdminService as;

    @PostMapping("/admin")
    public ResponseEntity<?> createAdmin(@RequestBody AdminDetails a){
    	try {
			as.createAdminInfo(a);
			return new ResponseEntity<AdminDetails>(a,HttpStatus.OK);
		} catch (ConstraintViolationException e) {
			return new ResponseEntity<>(e.getMessage(),HttpStatus.UNPROCESSABLE_ENTITY);
			// TODO: handle exception
		} catch (AdminException e) {
			return new ResponseEntity<>(e.getMessage(),HttpStatus.CONFLICT);
			// TODO: handle exception
		}
    }


    @GetMapping("/admin")
    public ResponseEntity<?> getAllAdminDetails()
    {
    	List<AdminDetails> a=as.getAllAdmin();
		return new ResponseEntity<>(a, a.size()>0 ? HttpStatus.OK : HttpStatus.NOT_FOUND);
    }


    @PutMapping("/admin/{id}")
    public ResponseEntity<?> updateByID(@PathVariable("id") String id, @RequestBody AdminDetails a) {
    	try {
			as.updateAdmin(id, a);
			return new ResponseEntity<>("update Farmer with id"+id, HttpStatus.OK);
		} catch (AdminException e) {
			// TODO: handle exception
			return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
		}
    }

    @DeleteMapping("/admin/{id}")
    public ResponseEntity<?> deleteById(@PathVariable("id") String id) {
    	try {
			as.deleteAdminById(id);
			return new ResponseEntity<>("Successfully deleted with id"+id,HttpStatus.OK);
		} catch (AdminException e) {
			return new ResponseEntity<>(e.getMessage(),HttpStatus.NOT_FOUND);
			// TODO: handle exception
		}
    }


}
