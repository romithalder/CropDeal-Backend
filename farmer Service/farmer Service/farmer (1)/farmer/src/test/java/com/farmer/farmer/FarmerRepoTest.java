package com.farmer.farmer;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.farmer.farmer.Repository.farmerRepo;
import com.farmer.farmer.model.farmer;


@ExtendWith(SpringExtension.class)
@SpringBootTest
public class FarmerRepoTest {
	
	
	@Autowired
	private farmerRepo fr;
	
	@Test
	public void FarmershouldReturnFarmerObject() {
		
		farmer f1 = new farmer("1", "Romit", "romithalder3000@gmail.com", "Kolkata", "7595989565", "roh2000"); //User input
		fr.save(f1); //Data is saved into Database
		farmer f2 = fr.findById(f1.getId()).get(); // Data is retrieved from Database
		assertNotNull(f2);
		assertEquals(f1.getName(), f2.getName());
			
	}

	@Test
	public void getAllmustReturnAllDepartments() {
		farmer f3 = new farmer("1", "Romit", "romithalder3000@gmail.com", "Kolkata", "7595989565", "roh2000");//User Input
		farmer f4 = new farmer("2", "abhinav", "abhinav3000@gmail.com", "patna", "6732489565", "abhi2000"); // User Input
		fr.save(f3); //save the Data in database
		fr.save(f4); // save the Data in Database
		List <farmer> farmerList = (List<farmer>) fr.findAll();
		assertEquals("Romit",farmerList.get(0).getName());
			
	}
	
	@Test
	   public void testFindById() {
	      farmer f= new farmer("5", "Preetam", "PreetamKundu@gmail.com", "Indore", "67234676234", "pret3272");
	      fr.save(f);
	      farmer result = fr.findById(f.getId()).get();
	      assertEquals(f.getId(), result.getId());	     
	   }
	
	@Test
	   public void testDeleteById() {
	      farmer far= new farmer("7", "Hrittick", "HritickKundu@gmail.com", "Hyderabad", "634676234", "Hrit3272");
	      fr.save(far);
	      fr.deleteById(far.getId());
	      List<farmer> result = new ArrayList<>();
	      fr.findAll().forEach(e -> result.add(e));
	      assertEquals(result.size(), 16);
	   }
	

}

