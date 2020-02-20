package com.tp.challenge.restapi.familym;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.data.repository.CrudRepository;

//public interface FamilyMRepository extends CrudRepository<FamilyM,Long> {
public interface FamilyMRepository extends JpaRepository<FamilyM,Long> {
		
	public List<FamilyM> findByFamilyId(long familyId);	
	public FamilyM findByFatherId(String fatherId);	
	public FamilyM findByMotherId(String motherId);

}

