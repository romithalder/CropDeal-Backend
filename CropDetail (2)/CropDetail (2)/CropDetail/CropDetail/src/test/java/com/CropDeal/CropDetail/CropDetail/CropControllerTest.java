package com.CropDeal.CropDetail.CropDetail;

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

import com.CropDeal.CropDetail.CropDetail.Service.CropDetailService;
import com.CropDeal.CropDetail.CropDetail.controller.cropController;
import com.CropDeal.CropDetail.CropDetail.model.cropDetail;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@ExtendWith(MockitoExtension.class)
public class CropControllerTest {
	
	@Autowired
    private MockMvc mockMvc;
	
    @Mock
    private CropDetailService cs;
    private cropDetail cd;
    private List<cropDetail> cropList;

    @InjectMocks
    private cropController cc;
    
    @BeforeEach
    public void setUp(){
        this.cd = new cropDetail("2","Fruits(Apple)","50tonnes","Kashmir");
        mockMvc= MockMvcBuilders.standaloneSetup(cc).build();
    }
    
    
    @Test
    public void CreateCropControllerTest() throws Exception {
    	cropDetail ad=new cropDetail();
		ad.setId("2");
		ad.setType("Fruits(Apple)");
		ad.setQuantity("40quintal");
		ad.setAddress("Kashmir");
    	
        when(cs.createCropDetail(any())).thenReturn(ad);
        mockMvc.perform(post("/crop")
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(ad)))
                .andExpect(status().isOk())
                ;
        

    }
    
    @Test
    public void getAllDealerControllerTest() throws Exception{
    	cropDetail ad=new cropDetail();
		ad.setId("2");
		ad.setType("Fruits(Apple)");
		ad.setQuantity("40quintal");
		ad.setAddress("Kashmir");
		
    	List<cropDetail> allcrop= Arrays.asList(ad);
    	when(cs.getAllCropDetail()).thenReturn(allcrop);
        mockMvc.perform(MockMvcRequestBuilders.get("/api/vi/crop")
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(ad)))
                .andExpect(status().isOk())
                .andDo(MockMvcResultHandlers.print());
        

    }
    
    @Test
    public void DeleteDealerControllerTest() throws Exception {
    	cropDetail ad=new cropDetail();
		ad.setId("2");
		ad.setType("Fruits(Apple)");
		ad.setQuantity("40quintal");
		ad.setAddress("Kashmir");
		doNothing().when(cs).deleteCropDetailById(ad.getId());
		mockMvc.perform(MockMvcRequestBuilders.delete("/crop/"+ad.getId().toString())
		.contentType(MediaType.APPLICATION_JSON))
		.andExpect(status().isOk());
    }
    
    @Test
    public void getDealerByIdController() throws Exception {
    	cropDetail ad=new cropDetail();
    	ad.setId("2");
		ad.setType("Fruits(Apple)");
		ad.setQuantity("40quintal");
		ad.setAddress("Kashmir");
		
		when(cs.getSingleCropDetail(ad.getId())).thenReturn(ad);
		mockMvc.perform(MockMvcRequestBuilders.get("/crop/"+ ad.getId().toString())
		.contentType(MediaType.APPLICATION_JSON))
		.andExpect(status().isOk());
		}
    
    @Test
    public void updateDealerControllerTest() throws JsonProcessingException, Exception {
    	cropDetail ad=new cropDetail();
    	ad.setId("2");
		ad.setType("Fruits(Apple)");
		ad.setQuantity("40quintal");
		ad.setAddress("Kashmir");
		
		doNothing().when(cs).updateCropDetail(ad.getType(), ad);
		ObjectMapper mapper = new ObjectMapper();
		mockMvc.perform(MockMvcRequestBuilders.put("/api/vi/crop/2")
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
