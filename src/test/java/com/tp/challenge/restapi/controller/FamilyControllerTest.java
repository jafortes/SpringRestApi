package com.tp.challenge.restapi.controller;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tp.challenge.restapi.family.Family;
import com.tp.challenge.restapi.family.FamilyController;
import com.tp.challenge.restapi.family.FamilyService;


@RunWith(SpringRunner.class)
@WebMvcTest(value = FamilyController.class)
public class FamilyControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private FamilyService fService;

	
	
	Family mockFamily = new Family(2,"Silva", "pt");
	

	@Test
	public void getFamilyDetails() throws Exception {
		String expected ="{\"id\":2,\"name\":\"Silva\",\"countrycode\":\"pt\"}";
		
		Mockito.when(
				fService.getFamily(Mockito.anyLong())).thenReturn(mockFamily);
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get(
				"/families/2").accept(
				MediaType.APPLICATION_JSON);
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();		
				
		JSONAssert.assertEquals(expected, result.getResponse()
				.getContentAsString(), false);
		
		System.out.println(result.getResponse());
	}
	
	


    @Test
    public void updateparcialFamily() throws Exception {
       
    	
        Mockito.when(fService.updparcialFamily(Mockito.anyLong()
        		,Mockito.any(Family.class))).thenReturn(mockFamily);

        mockMvc.perform(MockMvcRequestBuilders.post("/families/2")
        		.content(asJsonString(mockFamily))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());                                
    }
    
    
    @Test
    public void addFamily() throws Exception {
    	
    	
        Mockito.when(fService.addFamily(Mockito.any(Family.class)))
        .thenReturn(mockFamily);

        mockMvc.perform(MockMvcRequestBuilders.post("/families")
                .content(asJsonString(mockFamily))
                .contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())                
                .andExpect(content().contentType("application/json"))
        
                ;
        
    }

    public static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
