package com.farmer.farmer;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Optional;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.farmer.farmer.Repository.farmerRepo;
import com.farmer.farmer.exception.farmerInfoException;
import com.farmer.farmer.farmerService.farmerService;
import com.farmer.farmer.model.farmer;

@SpringBootTest
class FarmerServiceTests {
	
	@Autowired
	private farmerService fs;
	
	@MockBean
	private farmerRepo fr;

	@Test
	void contextLoads() {
	}
	
	@Test
	public void getdealerTest() {
		when(fr.findAll()).thenReturn(java.util.stream.Stream.of(new farmer("1", "Romit", "romithalder3000@gmail.com", "Kolkata", "7595989565", "roh2000"), new farmer("2", "Abhinav", "abhinav3000@gmail.com", "Bihar", "8634789565", "abhi2000")).collect(Collectors.toList()));
				
		assertEquals(2, fs.getAllFarmer().size());
	}
	
	@Test
	public void CreatedealerInfoTest() throws farmerInfoException {
		farmer ad=new farmer();
		ad.setId("2");
		ad.setName("abhinav");
		ad.setEmail("abhinav122gmail.com");
		ad.setAddress("patna");
		ad.setMobile("672346723");
		ad.setPassword("abhi2000");
		
		 when(fr.save(any(farmer.class))).thenReturn(ad);

	        farmer created = fs.createFarmerInfo(ad);
	        
	       
	        verify(fr, times(1)).save(any(farmer.class));
	        
	}
	
	@Test
	public void deletefarmerTest() throws farmerInfoException {
		farmer ad=new farmer();
		ad.setId("2");
		ad.setName("abhinav");
		ad.setEmail("abhinav122gmail.com");
		ad.setAddress("patna");
		ad.setMobile("672346723");
		ad.setPassword("abhi2000");
		
		when(fr.findById(ad.getId())).thenReturn(Optional.of(ad));
		fs.deleteFarmerById("2");
		verify(fr).deleteById("2");
	}
	
	@Test
	public void getdealerByIdTest() throws farmerInfoException {
		farmer ad=new farmer();
		ad.setId("3");
		ad.setName("Ranjan");
		ad.setEmail("Ranjan122gmail.com");
		ad.setAddress("Darbhanga");
		ad.setMobile("573246763");
		ad.setPassword("raju2000");
		
		when(fr.findById(ad.getId())).thenReturn(Optional.of(ad));
		farmer expected = fs.getSingleFarmer(ad.getId());
		verify(fr).findById("3");
	}

}
