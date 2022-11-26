package com.farmer.farmer;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.ArgumentMatchers.isNotNull;
import static org.mockito.ArgumentMatchers.isNull;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.ws.rs.PUT;

import org.apache.http.entity.ContentType;
import org.assertj.core.error.ShouldHaveSameSizeAs;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;


import com.farmer.farmer.controller.farmerController;
import com.farmer.farmer.exception.farmerInfoException;
import com.farmer.farmer.farmerService.farmerService;
import com.farmer.farmer.model.farmer;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jayway.jsonpath.JsonPath;

@ExtendWith(MockitoExtension.class)
public class FarrmercontrollerTest {

	
    @Autowired
    private MockMvc mockMvc;
	
    @Mock
    private farmerService fs;
    private farmer f;
    private List<farmer> farmerList;

    @InjectMocks
    private farmerController fc;
    
    @BeforeEach
    public void setUp(){
        this.f = new farmer("1", "Romit", "romithalder3000@gmail.com", "Kolkata", "7595989565", "roh2000");
        mockMvc= MockMvcBuilders.standaloneSetup(fc).build();
    }
    
    
    @Test
    public void CreateFarmerControllerTest() throws Exception {
    	farmer ad=new farmer();
		ad.setId("2");
		ad.setName("abhinav");
		ad.setEmail("abhinav122gmail.com");
		ad.setAddress("patna");
		ad.setMobile("672346723");
		ad.setPassword("abhi2000");
    	
        when(fs.createFarmerInfo(any())).thenReturn(ad);
        mockMvc.perform(post("/farmer")
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(ad)))
                .andExpect(status().isOk())
                ;
        

    }
    
//    @Test
//    public void getAllFarmerControllerTest() throws Exception{
//    	farmer ad=new farmer();
//		ad.setId("2");
//		ad.setName("abhinav");
//		ad.setEmail("abhinav122gmail.com");
//		ad.setAddress("patna");
//		ad.setMobile("672346723");
//		ad.setPassword("abhi2000");
//		
//    	List<farmer> allfarmers = Arrays.asList(ad);
//    	when(fs.getAllFarmer()).thenReturn(allfarmers);
//        mockMvc.perform(MockMvcRequestBuilders.get("/api/vi/farmer")
//                .contentType(MediaType.APPLICATION_JSON)
//                .content(asJsonString(f)))
//                .andExpect(status().isOk())
//                .andDo(MockMvcResultHandlers.print());
//        
//
//    }
    
    @Test
    public void deleteFarmerControllerTest() throws Exception {
    	farmer ad=new farmer();
		ad.setId("2");
		ad.setName("abhinav");
		ad.setEmail("abhinav122gmail.com");
		ad.setAddress("patna");
		ad.setMobile("672346723");
		ad.setPassword("abhi2000");
		doNothing().when(fs).deleteFarmerById(ad.getId());
		mockMvc.perform(MockMvcRequestBuilders.delete("/farmer/"+ad.getId().toString())
		.contentType(MediaType.APPLICATION_JSON))
		.andExpect(status().isOk());
    }
    
    @Test
    public void getFarmerByIdController() throws Exception {
    	farmer ad=new farmer();
		ad.setId("2");
		ad.setName("abhinav");
		ad.setEmail("abhinav122gmail.com");
		ad.setAddress("patna");
		ad.setMobile("672346723");
		ad.setPassword("abhi2000");
		
		when(fs.getSingleFarmer(ad.getId())).thenReturn(ad);
		mockMvc.perform(MockMvcRequestBuilders.get("/farmer/"+ ad.getId().toString())
		.contentType(MediaType.APPLICATION_JSON))
		.andExpect(status().isOk());
		}
    
//    @Test
//    public void updateFarmerControllerTest() throws JsonProcessingException, Exception {
//    	farmer ad=new farmer();
//		ad.setId("2");
//		ad.setName("abhinav");
//		ad.setEmail("abhinav122gmail.com");
//		ad.setAddress("patna");
//		ad.setMobile("672346723");
//		ad.setPassword("abhi2000");
//		
//		doNothing().when(fs).updateFarmer(ad.getName(), ad);
//		ObjectMapper mapper = new ObjectMapper();
//		mockMvc.perform(MockMvcRequestBuilders.put("/api/vi/farmer/2")
//				.content(mapper.writeValueAsString(ad))
//				.contentType(MediaType.APPLICATION_JSON))
//				.andExpect(status().isOk());
//		
//    }

    
    
    


	public static String asJsonString(final Object obj){
        try{
            return new ObjectMapper().writeValueAsString(obj);
        }catch(Exception e){
            throw new RuntimeException(e);
        }
    }
}
