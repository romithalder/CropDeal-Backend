package com.admin.details.admindetails;

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
import org.springframework.boot.autoconfigure.kafka.KafkaProperties.Admin;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.admin.details.admindetails.AdminController.AdminController;
import com.admin.details.admindetails.AdminService.AdminService;
import com.admin.details.admindetails.adminPackage.AdminDetails;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@ExtendWith(MockitoExtension.class)
public class AdminControllerTest {
	
	@Autowired
    private MockMvc mockMvc;
	
    @Mock
    private AdminService as;
    private AdminDetails a;
    private List<AdminDetails> adminList;

    @InjectMocks
    private AdminController ac;
    
    @BeforeEach
    public void setUp(){
        this.a = new AdminDetails("1", "Romit", "romithalder3000@gmail.com", "Kolkata", "7595989565", "roh2000");
        mockMvc= MockMvcBuilders.standaloneSetup(ac).build();
    }
    
    
    @Test
    public void CreateAdminControllerTest() throws Exception {
    	AdminDetails ad=new AdminDetails();
		ad.setUserId("2");
		ad.setName("abhinav");
		ad.setEmail("abhinav122gmail.com");
		ad.setAddress("patna");
		ad.setMobileNo("672346723");
		ad.setPassword("abhi2000");
    	
        when(as.createAdminInfo(any())).thenReturn(ad);
        mockMvc.perform(post("/admin")
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(ad)))
                .andExpect(status().isOk())
                ;
        

    }
    
    @Test
    public void getAllAdminControllerTest() throws Exception{
    	AdminDetails ad=new AdminDetails();
		ad.setUserId("2");
		ad.setName("abhinav");
		ad.setEmail("abhinav122gmail.com");
		ad.setAddress("patna");
		ad.setMobileNo("672346723");
		ad.setPassword("abhi2000");
		
    	List<AdminDetails> alladmin= Arrays.asList(ad);
    	when(as.getAllAdmin()).thenReturn(alladmin);
        mockMvc.perform(MockMvcRequestBuilders.get("/api/vi/admin")
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(ad)))
                .andExpect(status().isOk())
                .andDo(MockMvcResultHandlers.print());
        

    }
    
    @Test
    public void DeleteAdminControllerTest() throws Exception {
    	AdminDetails ad=new AdminDetails();
		ad.setUserId("2");
		ad.setName("abhinav");
		ad.setEmail("abhinav122gmail.com");
		ad.setAddress("patna");
		ad.setMobileNo("672346723");
		ad.setPassword("abhi2000");
		doNothing().when(as).deleteAdminById(ad.getUserId());
		mockMvc.perform(MockMvcRequestBuilders.delete("/admin/"+ad.getUserId().toString())
		.contentType(MediaType.APPLICATION_JSON))
		.andExpect(status().isOk());
    }
    
//    @Test
//    public void getAdminByIdController() throws Exception {
//    	AdminDetails ad=new AdminDetails();
//		ad.setUserId("2");
//		ad.setName("abhinav");
//		ad.setEmail("abhinav122gmail.com");
//		ad.setAddress("patna");
//		ad.setMobileNo("672346723");
//		ad.setPassword("abhi2000");
//		
//		when(as.getSingleAdmin(ad.getUserId())).thenReturn(ad);
//		mockMvc.perform(MockMvcRequestBuilders.get("/admin/"+ ad.getUserId().toString())
//		.contentType(MediaType.APPLICATION_JSON))
//		.andExpect(status().isOk());
//		}
    
    @Test
    public void updateAdminControllerTest() throws JsonProcessingException, Exception {
    	AdminDetails ad=new AdminDetails();
		ad.setUserId("2");
		ad.setName("abhinav");
		ad.setEmail("abhinav122gmail.com");
		ad.setAddress("patna");
		ad.setMobileNo("672346723");
		ad.setPassword("abhi2000");
		
		doNothing().when(as).updateAdmin(ad.getName(), ad);
		ObjectMapper mapper = new ObjectMapper();
		mockMvc.perform(MockMvcRequestBuilders.put("/api/vi/admin/2")
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
