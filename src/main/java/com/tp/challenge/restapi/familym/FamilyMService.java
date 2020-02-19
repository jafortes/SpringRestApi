package com.tp.challenge.restapi.familym;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.TreeMap;
import java.util.stream.Collectors;

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
    Map<String, TreeMap<FamilyM, String>> dicFTree = new HashMap<String, TreeMap<FamilyM, String>>();

    // Creating an empty TreeSet 
    //TreeSet<Integer> tset = new TreeSet<Integer>(); 
    
	/*private List<FamilyM> familiesM = new ArrayList<>(Arrays.asList(
			new FamilyM("1","1","jose","fortes","","", LocalDate.of(1973,10,1)),
			new FamilyM("2","1","joao","fortes","","", LocalDate.of(1976,12,7))
			));*/

	
	//public List<FamilyM> getAllFamiliesM(String familyId){
	public List<FamilyM> getAllFamiliesM(){
		//return families;
		List<FamilyM> familiesm = new ArrayList<>();
		familyMRepository.findAll()
		//familyMRepository.findByFamilyId(familyId)
		.forEach(familiesm::add);
		return familiesm;
	}
	
	public List<FamilyM> getAllFamiliesM(String familyId){	
		List<FamilyM> familiesm = new ArrayList<>();
		familyMRepository.findAll()
		//familyMRepository.findByFamilyId(familyId)
		.forEach(familiesm::add);
		return familiesm.stream().filter(f -> f.getFamilyID().equals(familyId)).collect(Collectors.toList());				
	}
	
	
	public FamilyM getFamilyM(long id){			
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
		removeMapTree(familym);
		familyMRepository.save(familym);
		putMapTree(familym);
	}

	public void updparcialFamilyM(long id, FamilyM familym) {		
		Optional<FamilyM> familymOptional = familyMRepository.findById(id);
		if (familymOptional.isPresent()){
			FamilyM f = familymOptional.get();
			removeMapTree(f);
			 f.Merge(familym); 	
			 familyMRepository.save(f);
			 putMapTree(f);
		}
		else{
		   throw new EntityNotFoundException(HttpStatus.NOT_FOUND +":"+id);
		}		
	}
	
	public boolean deleteFamilyM(long id) {
		Optional<FamilyM> familymOptional = familyMRepository.findById(id);
		if (familymOptional.isPresent()){				
			Optional<FamilyM> familymMother = familyMRepository.findByMotherId(familymOptional.get().getMotherId());
			Optional<FamilyM> familymfather = familyMRepository.findByFatherId(familymOptional.get().getFatherId());
			if (!familymMother.isPresent() && !familymfather.isPresent()){						
				familyMRepository.deleteById(id);
				removeMapTree(familymOptional.get());
				return true;
			}
		}
		return false;		
	}
	
	/*public boolean deleteFamilyM(long id) {
		Optional<FamilyM> familymOptional = familyMRepository.findById(id);
		if (familymOptional.isPresent()){			
			familyMRepository.deleteById(id);
			removeMapTree(familymOptional.get());
			return true;
		}
		return false;		
	}*/
	

	    public void putMapTree(FamilyM fm)
	    {	    	
	    	if (dicFTree.containsKey(fm.getFamilyID())) 
	    	{
	    		dicFTree.get(fm.getFamilyID()).put(fm, fm.getFamilyID());	    		
	    	}
	    	else 
	    	{
	    		TreeMap<FamilyM, String> FamilyTree  = new TreeMap<FamilyM, String>(new TreeMapComparator());
	    		FamilyTree.put(fm,fm.getFamilyID());
	    		dicFTree.put(fm.getFamilyID(),FamilyTree);
	    	}	    	
	    }


	    public void removeMapTree(FamilyM fm)
	    {   	    		    		    	
    		if (dicFTree.containsKey(fm.getFamilyID()))
    		{
    			String resp = dicFTree.get(fm.getFamilyID()).remove(fm);    			
    		}
	    }

	    

	    public int GetFamilyNumber(String familyid)
	    {
	    	if (dicFTree.containsKey(familyid))
    		{
    			dicFTree.get(familyid).size();	    				  
    		}
	    	return 0;
	    }
  
	    public List<FindFamily> GetFamilyGrowingRatio(String familyId)
	    {	   
	    	double res = 0;double difNewOld = 0;
	    	List<FindFamily> lsttmp = new ArrayList<>();
	    	List<FindFamily> lst = new ArrayList<>();
	    	for (String fid : dicFTree.keySet()) {
	    		res=0;
	    		if (dicFTree.get(fid).size() > 0) {
	    			difNewOld = dicFTree.get(fid).lastKey().getBirthYear() - dicFTree.get(fid).firstKey().getBirthYear();
	    			if (difNewOld > 0){
	    				res= dicFTree.get(fid).size() / (double)difNewOld;	    			
	    			}
	    		}
	    		if (res>0){	    			
	    			lsttmp.add(new FindFamily(fid,res)); 	
	    		}
	    	}
	    	FindFamily maxValue = lsttmp.stream().max(Comparator.comparing(v -> v.getResult())).get();	    	
	    	lst = lsttmp.stream().filter(b -> b.getResult() == maxValue.getResult())
	    	        .collect(Collectors.toList());	    	
	        return lst;
	    }


	    public List<FindFamily> GetFamilyAcumulatedAge(String criteria)
	    {
	    	long acumAge = 0;  
	    	List<FindFamily> lsttmp = new ArrayList<>();
	    	List<FindFamily> lst = new ArrayList<>(); 	              
	        for (String fid : dicFTree.keySet()) {	        	
	        	Set set = dicFTree.get(fid).entrySet();
	  	        Iterator iterator = set.iterator();
	  	        while(iterator.hasNext()) {
	  	           Map.Entry mentry = (Map.Entry)iterator.next();
	  	           FamilyM f = (FamilyM)(mentry.getKey());
	  	           acumAge += f.getAge();
	  	        }
	  	      lsttmp.add(new FindFamily(fid,acumAge));
	  	        acumAge = 0;	    		
	    	}
	        FindFamily maxValue = lsttmp.stream().max(Comparator.comparing(v -> v.getResult())).get();	    	
	    	lst = lsttmp.stream().filter(b -> b.getResult() == maxValue.getResult())
	    	        .collect(Collectors.toList());	    	
	        return lst;
	    }
	    
	    
	
}
