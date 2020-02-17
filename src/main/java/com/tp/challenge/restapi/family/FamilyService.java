package com.tp.challenge.restapi.family;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

@Service
public class FamilyService {


	
	private List<Family> families = new ArrayList<>(Arrays.asList(
			new Family("Santos","pt"),				
			new Family("Silva","pt"),
			new Family("Pinto","pt"),
			new Family("Sousa","es"),
			new Family("Cook","uk")
			));

	
	public List<Family> getAllFamilies(){
		return families;		
	}
	
	public List<Family> getFamiliesCCode(String countrycode){				
			return families.stream()
					.filter(f -> f.getCountrycode().equals(countrycode))
					.collect(Collectors.toList());				
	}

	public Family getFamilyName(String name){			
			return families.stream().filter(f -> f.getName().equals(name)).findFirst().get();			
	}
	
	public Family getFamily(String id){			
		return families.stream().filter(f -> f.getId().equals(id)).findFirst().get();			
}
	
	public void addFamily(Family family){
		families.add(family);		
	}


	
	public void updFamily(String id, Family family) {
		for(int i=0 ;  i<families.size() ;i++){
			Family f = families.get(i);
			if(f.getId().equals(id)) {					             			
				families.set(i, family);
				return;
			}
		}
	}

	public void updparcialFamily(String id, Family family) {
		for(int i=0 ;  i<families.size() ;i++){
			Family f = families.get(i);
			if(f.getId().equals(id)) {				
	            f.Merge(family); 				
				families.set(i, f);
				return;
			}
		}
	}
	

	public boolean delFamily(String id) {		
		Family temp = families.stream().filter(f -> f.getId().equals(id)).findFirst().get(); 
		if (temp  != null) {
			families.remove(temp);
			return true;
		}
		return false;
	}
	
}
