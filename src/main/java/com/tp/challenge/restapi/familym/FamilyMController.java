package com.tp.challenge.restapi.familym;

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

import com.tp.challenge.restapi.family.Family;

@RestController
public class FamilyMController {

	@Autowired
	private FamilyMService familyMService;
	
	
	@RequestMapping("/families/{id}/familiesm")
	public List<FamilyM> getAllFamiliesM(@PathVariable String id){		
		return familyMService.getAllFamiliesM(id);
	}
	

	
	@RequestMapping("/families/{familyid}/familiesm/{id}")
	@ResponseStatus(HttpStatus.OK)
	public FamilyM getFamilyM(@PathVariable String id){						 
		return familyMService.getFamilyM(id);			
	}
	
	/*@RequestMapping("/familiesmByName/{name}")
	@ResponseStatus(HttpStatus.OK)
	public FamilyM getFamilyName(@PathVariable String name){						 
		return familyMService.getFamilyName(name);			
	}*/
		
	@RequestMapping(method=RequestMethod.POST , value="/families/{familyid}/familiesm/")
	public ResponseEntity<?> addFamilyM(@RequestBody @Valid FamilyM familym, String familyid){
		familym.setFamily(new Family(familyid,familym.getLastname(),"pt"));
		familyMService.addFamilyM(familym);
		
		return ResponseEntity.ok("Resource POST");
	}
	
	@RequestMapping(method=RequestMethod.PATCH , value="/familiesm/{id}")
	public ResponseEntity<?> updateparcialFamilyM(@RequestBody @Valid FamilyM familym,@PathVariable String id){
		familyMService.updparcialFamilyM(id, familym);
		return ResponseEntity.ok("Resource PATCH");
	}
	
	@RequestMapping(method=RequestMethod.PUT , value="/families/{familyid}/familiesm/{id}")
	public ResponseEntity<?> updateFamilyM(@RequestBody @Valid FamilyM familym,String familyid,@PathVariable String id){
		familym.setFamily(new Family(familyid,familym.getLastname(),"pt"));
		familyMService.updFamilyM(familym);
		return ResponseEntity.ok("Resource PUT");
	}

	@RequestMapping(method=RequestMethod.DELETE , value="/families/{familyid}/familiesm/{id}")
	public ResponseEntity<?> deleteFamilyM(@PathVariable String id){		
		if (familyMService.deleteFamilyM(id)) {
			return ResponseEntity.ok("Resource DELETED");	
		}		
		return ResponseEntity.ok(HttpStatus.NOT_FOUND);
	}
	
}
