package com.CropDeal.CropDetail.CropDetail;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.CropDeal.CropDetail.CropDetail.Exception.CropDetailException;
import com.CropDeal.CropDetail.CropDetail.Repo.cropDetailRepository;
import com.CropDeal.CropDetail.CropDetail.Service.CropDetailService;
import com.CropDeal.CropDetail.CropDetail.model.cropDetail;

@SpringBootTest
class CropDetailServiceTests {

	@Autowired
	private CropDetailService dt;
	
	@MockBean
	private cropDetailRepository cr;
	
	@Test
	void contextLoads() {
	}
	
	@Test
	public void getcropTest() {
		when(dt.getAllCropDetail()).thenReturn(Stream
				.of(new cropDetail("1", "Romit", "romithalder3000@gmail.com", "Kolkata"), new cropDetail("2", "Abhinav", "abhinav3000@gmail.com", "Bihar")).collect(Collectors.toList()));
		assertEquals(2, dt.getAllCropDetail().size());
	}
	
	@Test
	public void CreatcropTest() throws CropDetailException {
		cropDetail ad=new cropDetail();
		ad.setId("2");
		ad.setQuantity("abhinav");
		ad.setType("abhinav122gmail.com");
		ad.setAddress("patna");
		
		
		
		 when(cr.save(any(cropDetail.class))).thenReturn(ad);

	        cropDetail created = dt.createCropDetail(ad);
	        
	       
	        verify(cr, times(1)).save(any(cropDetail.class));
	        
	}
	@Test
	public void deletecropTest() throws CropDetailException {
		cropDetail ad=new cropDetail();
		ad.setId("2");
		ad.setQuantity("abhinav");
		ad.setType("abhinav122gmail.com");
		ad.setAddress("patna");
		
		
		when(cr.findById(ad.getId())).thenReturn(Optional.of(ad));
		dt.deleteCropDetailById("2");
		verify(cr).deleteById("2");
	}
	
	@Test
	public void getdealerByIdTest() throws CropDetailException {
		cropDetail ad=new cropDetail();
		ad.setId("3");
		ad.setType("Ranjan");
		ad.setQuantity("Ranjan122gmail.com");
		ad.setAddress("Darbhanga");
	
		
		when(cr.findById(ad.getId())).thenReturn(Optional.of(ad));
		cropDetail expected = dt.getSingleCropDetail(ad.getId());
		verify(cr).findById("3");
	}

}
