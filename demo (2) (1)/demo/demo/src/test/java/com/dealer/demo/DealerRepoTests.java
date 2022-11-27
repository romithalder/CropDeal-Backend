package com.dealer.demo;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.dealer.demo.model.dealer;
import com.dealer.demo.repo.dealerrepo;



@ExtendWith(SpringExtension.class)
@SpringBootTest
public class DealerRepoTests {

	@Autowired
	private dealerrepo dr;
	
	@Test
	public void DealershouldReturnDealerObject() {
		
		dealer d1 = new dealer("1", "Romit", "romithalder3000@gmail.com", "Kolkata", "7595989565", "roh2000"); //User input
		dr.save(d1); //Data is saved into Database
		dealer d2 = dr.findById(d1.getId()).get(); // Data is retrieved from Database
		assertNotNull(d2);
		assertEquals(d1.getName(), d2.getName());
			
	}

	@Test
	public void getAllmustReturnAllDealer() {
		dealer d3 = new dealer("1", "Romit", "romithalder3000@gmail.com", "Kolkata", "7595989565", "roh2000");//User Input
		dealer d4 = new dealer("2", "abhinav", "abhinav3000@gmail.com", "patna", "6732489565", "abhi2000"); // User Input
		dr.save(d3); //save the Data in database
		dr.save(d4); // save the Data in Database
		List <dealer> dealerList = (List<dealer>) dr.findAll();
		assertEquals("Preetam",dealerList.get(2).getName());
			
	}
	
	@Test
	   public void testFindById() {
	      dealer d= new dealer("5", "Preetam", "PreetamKundu@gmail.com", "Indore", "67234676234", "pret3272");
	      dr.save(d);
	      dealer result = dr.findById(d.getId()).get();
	      assertEquals(d.getId(), result.getId());	     
	   }
	
	@Test
	   public void testDeleteById() {
	      dealer dea= new dealer("7", "Hrittick", "HritickKundu@gmail.com", "Hyderabad", "634676234", "Hrit3272");
	      dr.save(dea);
	      dr.deleteById(dea.getId());
	      List<dealer> result = new ArrayList<>();
	      dr.findAll().forEach(e -> result.add(e));
	      assertEquals(result.size(), 0);
	   }
}
