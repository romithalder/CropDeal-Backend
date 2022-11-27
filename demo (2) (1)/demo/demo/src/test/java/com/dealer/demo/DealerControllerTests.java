package com.dealer.demo;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Arrays;
import java.util.List;

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

import com.dealer.demo.controller.dealerController;
import com.dealer.demo.model.dealer;
import com.dealer.demo.service.dealerService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@ExtendWith(MockitoExtension.class)
public class DealerControllerTests {
	
	@Autowired
    private MockMvc mockMvc;
	
    @Mock
    private dealerService ds;
    private dealer d;
    private List<dealer> dealerList;

    @InjectMocks
    private dealerController dc;
    
    @BeforeEach
    public void setUp(){
        this.d = new dealer("1", "Romit", "romithalder3000@gmail.com", "Kolkata", "7595989565", "roh2000");
        mockMvc= MockMvcBuilders.standaloneSetup(dc).build();
    }
    
    
    @Test
    public void CreateDealerControllerTest() throws Exception {
    	dealer ad=new dealer();
		ad.setId("2");
		ad.setName("abhinav");
		ad.setEmail("abhinav122gmail.com");
		ad.setAddress("patna");
		ad.setMobile("672346723");
		ad.setPassword("abhi2000");
    	
        when(ds.createDealer(any())).thenReturn(ad);
        mockMvc.perform(post("/dealer")
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(ad)))
                .andExpect(status().isOk())
                ;
        

    }
    
    @Test
    public void getAllDealerControllerTest() throws Exception{
    	dealer ad=new dealer();
		ad.setId("2");
		ad.setName("abhinav");
		ad.setEmail("abhinav122gmail.com");
		ad.setAddress("patna");
		ad.setMobile("672346723");
		ad.setPassword("abhi2000");
		
    	List<dealer> alldealer= Arrays.asList(ad);
    	when(ds.getAlldealer()).thenReturn(alldealer);
        mockMvc.perform(MockMvcRequestBuilders.get("/api/vi/dealer")
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(ad)))
                .andExpect(status().isOk())
                .andDo(MockMvcResultHandlers.print());
        

    }
    
    @Test
    public void DeleteDealerControllerTest() throws Exception {
    	dealer ad=new dealer();
		ad.setId("2");
		ad.setName("abhinav");
		ad.setEmail("abhinav122gmail.com");
		ad.setAddress("patna");
		ad.setMobile("672346723");
		ad.setPassword("abhi2000");
		doNothing().when(ds).deleteDealerById(ad.getId());
		mockMvc.perform(MockMvcRequestBuilders.delete("/dealer/"+ad.getId().toString())
		.contentType(MediaType.APPLICATION_JSON))
		.andExpect(status().isOk());
    }
    
    @Test
    public void getDealerByIdController() throws Exception {
    	dealer ad=new dealer();
		ad.setId("2");
		ad.setName("abhinav");
		ad.setEmail("abhinav122gmail.com");
		ad.setAddress("patna");
		ad.setMobile("672346723");
		ad.setPassword("abhi2000");
		
		when(ds.getSingleDealer(ad.getId())).thenReturn(ad);
		mockMvc.perform(MockMvcRequestBuilders.get("/dealer/"+ ad.getId().toString())
		.contentType(MediaType.APPLICATION_JSON))
		.andExpect(status().isOk());
		}
    
    @Test
    public void updateDealerControllerTest() throws JsonProcessingException, Exception {
    	dealer ad=new dealer();
		ad.setId("2");
		ad.setName("abhinav");
		ad.setEmail("abhinav122gmail.com");
		ad.setAddress("patna");
		ad.setMobile("672346723");
		ad.setPassword("abhi2000");
		
		doNothing().when(ds).updateDealer(ad.getName(), ad);
		ObjectMapper mapper = new ObjectMapper();
		mockMvc.perform(MockMvcRequestBuilders.put("/api/vi/dealer/2")
				.content(mapper.writeValueAsString(ad))
				.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk());
		
    }

    
    
    


	public static String asJsonString(final Object obj){
        try{
            return new ObjectMapper().writeValueAsString(obj);
        }catch(Exception e){
            throw new RuntimeException(e);
        }
    }

}
