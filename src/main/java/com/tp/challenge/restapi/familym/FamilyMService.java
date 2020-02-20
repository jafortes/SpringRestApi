package com.tp.challenge.restapi.familym;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
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

import com.tp.challenge.restapi.family.Family;


@Service
public class FamilyMService implements FamilyMCollectionRepository{

	@Autowired	
	private FamilyMRepository familyMRepository;
		   
	private static  Map<String, TreeMap<FamilyM, Long>> dicFTree = new HashMap<String, TreeMap<FamilyM, Long>>();
    static {
    	//For testing purposes only sync with CommandLineRunner.MyRun    	
    	Family f1 = new Family(1,"Silva", "pt");
        Family f2 = new Family(2,"Moreira", "pt");        
        Family f3 = new Family(3,"ElChapo", "es");
        
        
    	FamilyM fm1 = new FamilyM("Rui", "Silva","1","2", LocalDate.of(1980, 11, 11),f1);
    	FamilyM fm2 = new FamilyM("Maria", "Silva","3","4", LocalDate.of(1990, 11, 11),f1);
    	FamilyM fm3 = new FamilyM("Pedro", "Moreira","5","6", LocalDate.of(1990, 11, 11),f2);
    	FamilyM fm4 = new FamilyM("Ana", "Moreira","7","8", LocalDate.of(2000, 11, 11),f2);
    	FamilyM fm5 = new FamilyM("Hernandez", "ElChapo","1","2", LocalDate.of(1980, 11, 11),f3);
        FamilyM fm6 = new FamilyM("Mariana", "ElChapo","3","4", LocalDate.of(1990, 11, 11),f3);
        
    	TreeMap<FamilyM, Long> FamilyTree1  = new TreeMap<FamilyM, Long>(new TreeMapComparator());
		FamilyTree1.put(fm1,fm1.getFamily().getid());
		FamilyTree1.put(fm2,fm2.getFamily().getid());
		dicFTree.put(String.valueOf(fm1.getFamily().getid()),FamilyTree1);
				
		TreeMap<FamilyM, Long> FamilyTree2  = new TreeMap<FamilyM, Long>(new TreeMapComparator());
		FamilyTree2.put(fm3,fm3.getFamily().getid());
		FamilyTree2.put(fm4,fm4.getFamily().getid());
		dicFTree.put(String.valueOf(fm3.getFamily().getid()),FamilyTree2);
		
		TreeMap<FamilyM, Long> FamilyTree3  = new TreeMap<FamilyM, Long>(new TreeMapComparator());
		FamilyTree3.put(fm5,fm5.getFamily().getid());
		FamilyTree3.put(fm6,fm6.getFamily().getid());
		dicFTree.put(String.valueOf(fm5.getFamily().getid()),FamilyTree3);
				
    }
    

    @Override
    public Collection<TreeMap<FamilyM, Long>> getMTreeFamiliesM() {
       return dicFTree.values();
    }
    

	public List<FamilyM> getAllFamiliesM(){
		List<FamilyM> familiesm = new ArrayList<>();		
		familyMRepository.findAll()
		.forEach(familiesm::add);
		return familiesm;
	}
	
	public List<FamilyM> getAllFamiliesmByFamilyId(String Id){	
		List<FamilyM> familiesm = new ArrayList<>();		
		familyMRepository.findByFamilyId(Long.valueOf(Id))
		.forEach(familiesm::add);
		return familiesm;
	}
	
	
	public FamilyM getFamilyM(long id){			 		
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
			FamilyM familymMother = familyMRepository.findByMotherId(familymOptional.get().getMotherId());
			FamilyM familymfather = familyMRepository.findByFatherId(familymOptional.get().getFatherId());
			if (familymMother != null && familymfather  != null ){						
				familyMRepository.deleteById(id);
				removeMapTree(familymOptional.get());
				return true;
			}
		}
		return false;		
	}

	

	    public void putMapTree(FamilyM fm)
	    {	    	
	    	if (dicFTree.containsKey(String.valueOf(fm.getFamily().getid()))) 
	    	{
	    		dicFTree.get(String.valueOf(fm.getFamily().getid())).put(fm, fm.getFamily().getid());	    		
	    	}
	    	else 
	    	{
	    		TreeMap<FamilyM, Long> FamilyTree  = new TreeMap<FamilyM, Long>(new TreeMapComparator());
	    		FamilyTree.put(fm,fm.getFamily().getid());
	    		dicFTree.put(String.valueOf(fm.getFamily().getid()),FamilyTree);
	    	}	    	
	    }


	    public void removeMapTree(FamilyM fm)
	    {   	    		    		    	
    		if (dicFTree.containsKey(String.valueOf(fm.getFamily().getid())))
    		{    			
				Long resp = dicFTree.get(String.valueOf(fm.getFamily().getid())).remove(fm);				
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
