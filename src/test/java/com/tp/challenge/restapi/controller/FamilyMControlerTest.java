package com.tp.challenge.restapi.controller;

import java.time.LocalDate;


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

import com.tp.challenge.restapi.family.Family;
import com.tp.challenge.restapi.familym.FamilyM;
import com.tp.challenge.restapi.familym.FamilyMController;
import com.tp.challenge.restapi.familym.FamilyMService;


@RunWith(SpringRunner.class)
@WebMvcTest(value = FamilyMController.class)
public class FamilyMControlerTest {
	
	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private FamilyMService fmService;

	FamilyM mockFamilyM = new FamilyM("Rui", "Silva","1","2", LocalDate.of(1980, 11, 11),new Family(1,"Silva", "pt"));
	
	@Test
	public void getFamilyMDetails() throws Exception {

		Mockito.when(
				fmService.getFamilyM(Mockito.anyLong())).thenReturn(mockFamilyM);
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get(
				"/familiesm/1").accept(
				MediaType.APPLICATION_JSON);
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();		
		String expected = "{\"fId\":0,\"firstname\":\"Rui\",\"lastname\":\"Silva\",\"fatherId\":\"1\",\"motherId\":\"2\",\"dateofbirth\":\"11-11-1980\",\"family\":{\"id\":1,\"name\":\"Silva\",\"countrycode\":\"pt\"},\"birthYear\":1980,\"age\":40.0}";
		JSONAssert.assertEquals(expected, result.getResponse()
				.getContentAsString(), false);
		
		System.out.println(result.getResponse());
	}

/*	@Test
	public void createFamilyM() throws Exception {
		
		FamilyM mockFamilyM = new FamilyM("Mariana", "Silva","1","2", LocalDate.of(1980, 11, 11),new Family(1,"Silva", "pt"));
		
		
		// studentService.addCourse to respond back with mockCourse
		Mockito.when(
				fmService.addFamilyM(Mockito.any(FamilyM.class)
						,Mockito.anyString())).thenReturn(mockFamilyM);

		// Send course as body to /students/Student1/courses
		RequestBuilder requestBuilder = MockMvcRequestBuilders
				.post("/students/Student1/courses")
				.accept(MediaType.APPLICATION_JSON).content(exampleCourseJson)
				.contentType(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();

		MockHttpServletResponse response = result.getResponse();

		assertEquals(HttpStatus.CREATED.value(), response.getStatus());

		assertEquals("http://localhost/students/Student1/courses/1",
				response.getHeader(HttpHeaders.LOCATION));

	}*/
	
}
