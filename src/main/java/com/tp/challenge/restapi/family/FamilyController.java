package com.tp.challenge.restapi.family;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
//import org.springframework.http.ResponseEntity.BodyBuilder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FamilyController {

	@Autowired
	private FamilyService familyService;
	
	
	@RequestMapping("/families")
	public List<Family> getAllFamilies(){		
		return familyService.getAllFamilies();
	}
	
	/*@RequestMapping("/families/{id}")
	@ResponseStatus(HttpStatus.OK)
	public Family getFamily(
			@PathVariable String id,
			@RequestParam(required = true, defaultValue = "Unknown") String name,
            @RequestParam(required = false) String countrycode
			){						 
		return familyService.getFamily(id,countrycode);			
	    //return repository.findById(id).orElseThrow(() -> new EmployeeNotFoundException(id));
	}*/
	
	@RequestMapping("/familiesByCountryCode/{countrycode}")
	@ResponseStatus(HttpStatus.OK)
	public List<Family> getFamilyCCode(@PathVariable String countrycode){						 
		return familyService.getFamiliesCCode(countrycode);			
	    //return repository.findById(id).orElseThrow(() -> new EmployeeNotFoundException(id));
	}
	
	@RequestMapping("/families/{id}")
	@ResponseStatus(HttpStatus.OK)
	public Family getFamily(@PathVariable String id){						 
		return familyService.getFamily(id);			
	}
	
	@RequestMapping("/familiesByName/{name}")
	@ResponseStatus(HttpStatus.OK)
	public Family getFamilyName(@PathVariable String name){						 
		return familyService.getFamilyName(name);			
	}
		
	@RequestMapping(method=RequestMethod.POST , value="/families")
	public ResponseEntity<?> addFamily(@RequestBody @Valid Family family){
		familyService.addFamily(family);
		return ResponseEntity.ok("Resource POST");
	}
	
	@RequestMapping(method=RequestMethod.PATCH , value="/families/{id}")
	public ResponseEntity<?> updateparcialFamily(@RequestBody @Valid Family family,@PathVariable String id){
		familyService.updparcialFamily(id, family);
		return ResponseEntity.ok("Resource PATCH");
	}
	
	@RequestMapping(method=RequestMethod.PUT , value="/families/{id}")
	public ResponseEntity<?> updateFamily(@RequestBody @Valid Family family,@PathVariable String id){
		familyService.updFamily(id,family);
		return ResponseEntity.ok("Resource PUT");
	}

	/*@RequestMapping(method=RequestMethod.DELETE , value="/families/{id}")
	public void deleteFamily(@PathVariable String id){
		familyService.deleteFamily(id);		
	}*/
	
	@RequestMapping(method=RequestMethod.DELETE , value="/families/{id}")
	public ResponseEntity<?> delFamily(@PathVariable String id){		
		if (familyService.delFamily(id)) {
			return ResponseEntity.ok("Resource DELETED");	
		}		
		return ResponseEntity.ok(HttpStatus.NOT_FOUND);
	}
}
