package com.admin.details.admindetails;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.admin.details.admindetails.AdminRepository.AdminRepository;
import com.admin.details.admindetails.adminPackage.AdminDetails;



@ExtendWith(SpringExtension.class)
@SpringBootTest
public class AdminRepoTest {
	
	@Autowired
	private AdminRepository ar;
	
	@Test
	public void DealershouldReturnDealerObject() {
		
		AdminDetails ad1 = new AdminDetails("1", "Romit", "romithalder3000@gmail.com", "Kolkata", "7595989565", "roh2000"); //User input
		ar.save(ad1); //Data is saved into Database
		AdminDetails ad2 = ar.findById(ad1.getUserId()).get(); // Data is retrieved from Database
		assertNotNull(ad2);
		assertEquals(ad1.getName(), ad2.getName());
			
	}

	@Test
	public void getAllmustReturnAllDealer() {
		AdminDetails ad3 = new AdminDetails("1", "Romit", "romithalder3000@gmail.com", "Kolkata", "7595989565", "roh2000");
		AdminDetails ad4 = new AdminDetails("5", "Supriyo", "Supriyo@gmail.com", "Siliguri", "673246763474", "hud2000");
		ar.save(ad3); //save the Data in database
		ar.save(ad4); // save the Data in Database
		List <AdminDetails> adminList = (List<AdminDetails>) ar.findAll();
		assertEquals("Romit",adminList.get(1).getName());
			
	}
	
	@Test
	   public void testFindById() {
		AdminDetails ad1 = new AdminDetails("1", "Romit", "romithalder3000@gmail.com", "Kolkata", "7595989565", "roh2000");
	      ar.save(ad1);
	      AdminDetails result = ar.findById(ad1.getUserId()).get();
	      assertEquals(ad1.getUserId(), result.getUserId());	     
	   }
	
	@Test
	   public void testDeleteById() {
		AdminDetails ad1 = new AdminDetails("1", "Rohit", "Rohit0@gmail.com", "Mumbai", "7595989565", "rohit1000");
	      ar.save(ad1);
	      ar.deleteById(ad1.getUserId());
	      List<AdminDetails> result = new ArrayList<>();
	      ar.findAll().forEach(e -> result.add(e));
	      assertEquals(result.size(), 0);
	   }

}
