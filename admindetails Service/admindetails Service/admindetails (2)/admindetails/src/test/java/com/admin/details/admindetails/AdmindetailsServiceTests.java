package com.admin.details.admindetails;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.RETURNS_MOCKS;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoInteractions;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import com.admin.details.admindetails.AdminException.AdminException;
import com.admin.details.admindetails.AdminRepository.AdminRepository;
import com.admin.details.admindetails.AdminService.AdminService;
import com.admin.details.admindetails.adminPackage.AdminDetails;

@RunWith(SpringRunner.class)
@SpringBootTest
class AdmindetailsServiceTests {

	@Autowired
	private AdminService as;
	
	@MockBean
	private AdminRepository ar;
	
	@Test
	void contextLoads() {
	}
	
	@Test
	public void getAdminTest() {
		when(ar.findAll()).thenReturn(Stream
				.of(new AdminDetails("1", "Romit", "romithalder3000@gmail.com", "Kolkata", "7595989565", "roh2000"), new AdminDetails("2", "Abhinav", "abhinav3000@gmail.com", "Bihar", "8634789565", "abhi2000")).collect(Collectors.toList()));
		assertEquals(2, as.getAllAdmin().size());
	}
	
	
	
	@Test
	public void CreateAdminInfoTest() throws AdminException {
		AdminDetails ad=new AdminDetails();
		ad.setUserId("2");
		ad.setName("abhinav");
		ad.setEmail("abhinav122gmail.com");
		ad.setAddress("patna");
		ad.setMobileNo("672346723");
		ad.setPassword("abhi2000");
		
		 when(ar.save(any(AdminDetails.class))).thenReturn(ad);

	        AdminDetails created = as.createAdminInfo(ad);
	        
	       
	        verify(ar, times(1)).save(any(AdminDetails.class));
	        
	}
	
	@Test
	public void deleteAdminTest() throws AdminException {
		AdminDetails ad=new AdminDetails();
		ad.setUserId("2");
		ad.setName("abhinav");
		ad.setEmail("abhinav122gmail.com");
		ad.setAddress("patna");
		ad.setMobileNo("672346723");
		ad.setPassword("abhi2000");
		
		when(ar.findById(ad.getUserId())).thenReturn(Optional.of(ad));
		as.deleteAdminById("2");
		verify(ar).deleteById("2");
	}
	
	@Test
	public void getAdminByIdTest() throws AdminException {
		AdminDetails ad=new AdminDetails();
		ad.setUserId("3");
		ad.setName("Ranjan");
		ad.setEmail("Ranjan122gmail.com");
		ad.setAddress("Darbhanga");
		ad.setMobileNo("573246763");
		ad.setPassword("raju2000");
		
		when(ar.findById(ad.getUserId())).thenReturn(Optional.of(ad));
		AdminDetails expected = as.getSingleAdmin(ad.getUserId());
		verify(ar).findById("3");
	}


}
