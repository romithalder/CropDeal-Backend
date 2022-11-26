package com.CropDeal.CropDetail.CropDetail;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.CropDeal.CropDetail.CropDetail.Repo.cropDetailRepository;
import com.CropDeal.CropDetail.CropDetail.model.cropDetail;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class CropRepoTest {
	
	@Autowired
	private cropDetailRepository cr;
	
	@Test
	public void CropshouldReturnCropObject() {
		
		cropDetail cd1 = new cropDetail("2","Fruits(Apple)","50tonnes","Kashmir"); //User input
		cr.save(cd1); //Data is saved into Database
		cropDetail cd2 = cr.findById(cd1.getId()).get(); // Data is retrieved from Database
		assertNotNull(cd2);
		assertEquals(cd1.getType(), cd2.getType());
			
	}

	@Test
	public void getAllmustReturnAllDealer() {
		cropDetail cd3 = new cropDetail("2","Fruits(Apple)","50tonnes","Kashmir");//User Input
		cropDetail cd4 = new cropDetail("3","vegetable(carrot)","20tonnes","MP"); // User Input
		cr.save(cd3); //save the Data in database
		cr.save(cd4); // save the Data in Database
		List <cropDetail> dealerList = (List<cropDetail>) cr.findAll();
		assertEquals("vegetable(carrot)",dealerList.get(2).getType());
			
	}
	
	@Test
	   public void testFindById() {
	      cropDetail cd= new cropDetail("2","Fruits(Orange)","70tonnes","UP");
	      cr.save(cd);
	      cropDetail result = cr.findById(cd.getId()).get();
	      assertEquals(cd.getId(), result.getId());	     
	   }
	
	@Test
	   public void testDeleteById() {
	      cropDetail cde= new cropDetail("2","vegetable(onions)","20quintal","Maharashtra");
	      cr.save(cde);
	      cr.deleteById(cde.getId());
	      List<cropDetail> result = new ArrayList<>();
	      cr.findAll().forEach(e -> result.add(e));
	      assertEquals(result.size(), 2);
	   }

}
