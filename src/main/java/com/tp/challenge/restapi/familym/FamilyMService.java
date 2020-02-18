package com.tp.challenge.restapi.familym;

//import java.time.LocalDate;
import java.util.ArrayList;
//import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.TreeMap;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

//import com.tp.challenge.restapi.family.Family;
//import com.tp.challenge.restapi.family.FamilyRepository;

@Service
public class FamilyMService {

	@Autowired
	private FamilyMRepository familyMRepository;
	
	// Creating an empty TreeMap more eficient to search
    private TreeMap<FamilyM, String> FamilyTree  = new TreeMap<FamilyM, String>(new TreeMapComparator());   
    
	
	/*private List<FamilyM> familiesM = new ArrayList<>(Arrays.asList(
			new FamilyM("1","1","jose","fortes","","", LocalDate.of(1973,10,1)),
			new FamilyM("2","1","joao","fortes","","", LocalDate.of(1976,12,7))
			));*/

	
	public List<FamilyM> getAllFamiliesM(String familyId){
		//return families;
		List<FamilyM> familiesm = new ArrayList<>();
		//familyMRepository.findAll()
		familyMRepository.findByFamilyId(familyId)
		.forEach(familiesm::add);
		return familiesm;
	}
	
	
	public FamilyM getFamilyM(String id){			
		//return families.stream().filter(f -> f.getId().equals(id)).findFirst().get();
		//return familyRepository.findById(id)
		//        .orElseThrow(() -> new EntityNotFoundException(id)); 		
		Optional<FamilyM> familymOptional = familyMRepository.findById(id);
		if (familymOptional.isPresent()){
			FamilyM f = familymOptional.get();
		    return f;
		}
		else{
		   throw new EntityNotFoundException(HttpStatus.NOT_FOUND +":"+ id);
		}	
	}

	
	public void addFamilyM(FamilyM familym){		
		familyMRepository.save(familym);
		putMapTree(familym);
	}


	
	public void updFamilyM(FamilyM familym) {
		familyMRepository.save(familym);		
	}

	public void updparcialFamilyM(String id, FamilyM familym) {		
		Optional<FamilyM> familymOptional = familyMRepository.findById(id);
		if (familymOptional.isPresent()){
			FamilyM f = familymOptional.get();
			 f.Merge(familym); 	
			 familyMRepository.save(f);
		}
		else{
		   throw new EntityNotFoundException(HttpStatus.NOT_FOUND +":"+id);
		}		
	}
	
	
	public boolean deleteFamilyM(String id) {
		Optional<FamilyM> familymOptional = familyMRepository.findById(id);
		if (familymOptional.isPresent()){
			familyMRepository.deleteById(id);
			removeMapTree(familymOptional.get());
			return true;
		}
		return false;		
	}
	


	    public void putMapTree(FamilyM fm)
	    {
	    	FamilyTree.put(fm,fm.getId());
	    }



	    public void removeMapTree(FamilyM fm)
	    {      
	    	String resp = FamilyTree.remove(fm);
	    }


	    public int GetFamilyNumber()
	    {
	        return FamilyTree == null ? 0 : FamilyTree.size();
	    }

	    public double GetFamilyGrowingRatio()
	    {
	    	if (FamilyTree == null || FamilyTree.size() ==0) return 0;
	        int difNewOld = FamilyTree.lastKey().getBirthYear() - FamilyTree.firstKey().getBirthYear();
	        return FamilyTree.size() / difNewOld;    	
	    }



	    public long GetFamilyAcumulatedAge()
	    {
	        long acumAge = 0;        
	        for (int i = 0; i < FamilyTree.size(); i++)
	        {
	            //acumAge += FamilyTree.values();        	
	        }
	                
	        /*Set set = FamilyTree.entrySet();
	        Iterator iterator = set.iterator();
	        while(iterator.hasNext()) {
	           Map.Entry mentry = (Map.Entry)iterator.next();
	           //System.out.print("key is: "+ mentry.getKey() + " & Value is: ");
	           //System.out.println(mentry.getValue());
	        }
	        */
	        return acumAge;
	    }
		
		
}
