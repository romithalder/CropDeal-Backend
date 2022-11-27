package com.dealer.demo;

import static org.junit.jupiter.api.Assertions.assertEquals;
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

import com.dealer.demo.dealerException.dealerException;
import com.dealer.demo.model.dealer;
import com.dealer.demo.repo.dealerrepo;
import com.dealer.demo.service.dealerService;

@SpringBootTest
class DemoServiceTests {

	@Autowired
	private dealerService ds;
	
	@MockBean
	private dealerrepo dr;
	
	@Test
	void contextLoads() {
	}
	
	@Test
	public void getdealerTest() {
		when(dr.findAll()).thenReturn(java.util.stream.Stream.of(new dealer("1", "Romit", "romithalder3000@gmail.com", "Kolkata", "7595989565", "roh2000"), new dealer("2", "Abhinav", "abhinav3000@gmail.com", "Bihar", "8634789565", "abhi2000")).collect(Collectors.toList()));
				
		assertEquals(2, ds.getAlldealer().size());
	}
	
	
	
	@Test
	public void CreatedealerInfoTest() throws dealerException {
		dealer ad=new dealer();
		ad.setId("2");
		ad.setName("abhinav");
		ad.setEmail("abhinav122gmail.com");
		ad.setAddress("patna");
		ad.setMobile("672346723");
		ad.setPassword("abhi2000");
		
		 when(dr.save(any(dealer.class))).thenReturn(ad);

	        dealer created = ds.createDealer(ad);
	        
	       
	        verify(dr, times(1)).save(any(dealer.class));
	        
	}
	
	@Test
	public void deletedealerTest() throws dealerException {
		dealer ad=new dealer();
		ad.setId("2");
		ad.setName("abhinav");
		ad.setEmail("abhinav122gmail.com");
		ad.setAddress("patna");
		ad.setMobile("672346723");
		ad.setPassword("abhi2000");
		
		when(dr.findById(ad.getId())).thenReturn(Optional.of(ad));
		ds.deleteDealerById("2");
		verify(dr).deleteById("2");
	}
	
	@Test
	public void getdealerByIdTest() throws dealerException {
		dealer ad=new dealer();
		ad.setId("3");
		ad.setName("Ranjan");
		ad.setEmail("Ranjan122gmail.com");
		ad.setAddress("Darbhanga");
		ad.setMobile("573246763");
		ad.setPassword("raju2000");
		
		when(dr.findById(ad.getId())).thenReturn(Optional.of(ad));
		dealer expected = ds.getSingleDealer(ad.getId());
		verify(dr).findById("3");
	}
	
//	@Test
//	public void updatedealerTest() throws dealerException {
//		
//		dealer ad=new dealer();
//		ad.setId("3");
//		ad.setName("Ranjan");
//		ad.setEmail("Ranjan122gmail.com");
//		ad.setAddress("Darbhanga");
//		ad.setMobile("573246763");
//		ad.setPassword("raju2000");
//		
//		dealer newUser = new dealer();
//		newUser.setName("Akash");
//		//given(dr.findById(ad.getId())).willReturn(Optional.of(ad));
//		ds.updateDealer(ad.getId(), newUser);
//		verify(dr).save(newUser);
//		verify(dr).findById(ad.getId());
//		}

}
