package com.tp.challenge.restapi.family;

import java.util.Arrays;
import java.util.List;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FamilyController {

	@RequestMapping("/families")
	public List<Family> getAllFamilies(){		
		return Arrays.asList(
				new Family("Santos","pt"),				
				new Family("Silva","pt"),
				new Family("Pinto","pt"),
				new Family("Sousa","es"),
				new Family("Cook","uk")
				);
	}
	
}
