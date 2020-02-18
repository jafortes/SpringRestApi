package com.tp.challenge.restapi.family;

import java.util.ArrayList;
//import java.util.Arrays;
import java.util.List;
import java.util.Optional;
//import java.util.stream.Collectors;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
public class FamilyService {
	
	@Autowired
	private FamilyRepository familyRepository;
	
/*	public List<Family> families = new ArrayList<>(Arrays.asList(
			new Family("Santos","pt"),				
			new Family("Silva","pt"),
			new Family("Pinto","pt"),
			new Family("Sousa","es"),
			new Family("Cook","uk")
			));*/

	
	public List<Family> getAllFamilies(){
		//return families;
		List<Family> families = new ArrayList<>();
		familyRepository.findAll()
		.forEach(families::add);
		return families;
	}
	
	public List<Family> getFamiliesCCode(String countrycode){
		return familyRepository.findByCountrycode(countrycode);
	}

	public Family getFamilyName(String name){
		return familyRepository.findByName(name);
		//List<Family> families = new ArrayList<>();
		//familyRepository.findAll()
		//.forEach(families::add);		
		//return families.stream().filter(f -> f.getName().equals(name)).findFirst().get();		
	}
	
	public Family getFamily(String id){			
		//return families.stream().filter(f -> f.getId().equals(id)).findFirst().get();
		//return familyRepository.findById(id)
		//        .orElseThrow(() -> new EntityNotFoundException(id)); 		
		Optional<Family> familyOptional = familyRepository.findById(id);
		if (familyOptional.isPresent()){
			Family f = familyOptional.get();
		    return f;
		}
		else{
		   throw new EntityNotFoundException(HttpStatus.NOT_FOUND +":"+ id);
		}	
	}
	
	public void addFamily(Family family){
		//families.add(family);	
		familyRepository.save(family);
	}


	
	public void updFamily(String id, Family family) {
		familyRepository.save(family);
		/*for(int i=0 ;  i<families.size() ;i++){
			Family f = families.get(i);
			if(f.getId().equals(id)) {					             			
				families.set(i, family);
				return;
			}
		}*/
	}

	public void updparcialFamily(String id, Family family) {
		/*List<Family> families = new ArrayList<>();
		familyRepository.findAll()
		.forEach(families::add);
		for(int i=0 ;  i<families.size() ;i++){
			Family f = families.get(i);
			if(f.getId().equals(id)) {				
	            f.Merge(family); 				
				families.set(i, f);
				return;
			}
		}*/
		
		Optional<Family> familyOptional = familyRepository.findById(id);
		if (familyOptional.isPresent()){
			Family f = familyOptional.get();
			 f.Merge(family); 	
				familyRepository.save(f);
		}
		else{
			throw new EntityNotFoundException(HttpStatus.NOT_FOUND +":"+ id);
		}		
	}
	

	public boolean delFamily(String id) {				
		Optional<Family> familyOptional = familyRepository.findById(id);
		if (familyOptional.isPresent()){
			familyRepository.deleteById(id);
			return true;
		}		
		return false;
	}
	
	public void deleteFamily(String id) {
		Optional<Family> familyOptional = familyRepository.findById(id);
		if (familyOptional.isPresent()){
			familyRepository.deleteById(id);			
		}
		else{
			throw new EntityNotFoundException(HttpStatus.NOT_FOUND +":"+ id);
		}	
		
	}
}
