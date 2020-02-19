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
	
	@RequestMapping("/familiesm/GRatio/{criteria}")
	public List<FindFamily> GetFamilyGRatio(@PathVariable String criteria){		
		return familyMService.GetFamilyGrowingRatio(criteria);
	}
	
	@RequestMapping("/familiesm/GetFAAge/{criteria}")
	public List<FindFamily> GetAcumulatedAge(@PathVariable String criteria){		
		return familyMService.GetFamilyAcumulatedAge(criteria);
	}

	@RequestMapping("/familiesm")
	public List<FamilyM> getAllFamiliesM(){		
		return familyMService.getAllFamiliesM();
	}
	
	
	@RequestMapping("/familiesm/{id}")
	@ResponseStatus(HttpStatus.OK)
	public FamilyM getFamilyM(@PathVariable long id){						 
		return familyMService.getFamilyM(id);			
	}

	@RequestMapping(method=RequestMethod.POST , value="/familiesm")
	public ResponseEntity<?> addFamilyM(@RequestBody @Valid FamilyM familym, String family_id){
		familyMService.addFamilyM(familym);		
		return ResponseEntity.ok("Resource POST");
	}
	
	@RequestMapping(method=RequestMethod.DELETE , value="/familiesm/{id}")
	public ResponseEntity<?> deleteFamilyM(@PathVariable long id){		
		if (familyMService.deleteFamilyM(id)) {
			return ResponseEntity.ok("Resource DELETED");	
		}		
		return ResponseEntity.ok(HttpStatus.NOT_FOUND);
	}
	
	@RequestMapping(method=RequestMethod.PATCH , value="/familiesm/{id}")
	public ResponseEntity<?> updateparcialFamilyM(@RequestBody @Valid FamilyM familym,@PathVariable long id){
		familyMService.updparcialFamilyM(id, familym);
		return ResponseEntity.ok("Resource PATCH");
	}
	
	@RequestMapping(method=RequestMethod.PUT , value="/familiesm/{id}")
	public ResponseEntity<?> updateFamilyM(@RequestBody @Valid FamilyM familym,String familyid,@PathVariable String id){
		familyMService.updFamilyM(familym);
		return ResponseEntity.ok("Resource PUT");
	}
	
	
	
	
	
	
	
	/*@RequestMapping(method=RequestMethod.POST , value="/families/{familyid}/familiesm")
	public ResponseEntity<?> addFamilyM(@RequestBody @Valid FamilyM familym, String familyid){
		//familym.setFamily(new Family(familym.getLastname(),"pt"));
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
		//familym.setFamily(new Family(familym.getLastname(),"pt"));
		familyMService.updFamilyM(familym);
		return ResponseEntity.ok("Resource PUT");
	}

	@RequestMapping(method=RequestMethod.DELETE , value="/families/{familyid}/familiesm/{id}")
	public ResponseEntity<?> deleteFamilyM(@PathVariable String id){		
		if (familyMService.deleteFamilyM(id)) {
			return ResponseEntity.ok("Resource DELETED");	
		}		
		return ResponseEntity.ok(HttpStatus.NOT_FOUND);
	}*/
	
}
