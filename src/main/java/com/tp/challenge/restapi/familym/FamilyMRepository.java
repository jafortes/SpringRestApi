package com.tp.challenge.restapi.familym;
import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.tp.challenge.restapi.family.Family;

public interface FamilyMRepository extends CrudRepository<FamilyM,Long> {

	//public List<FamilyM> findByFamilyId(String familyId);
	
	Optional<FamilyM> findByFatherId(String fatherId);	
	Optional<FamilyM> findByMotherId(String motherId);
}

