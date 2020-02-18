package com.tp.challenge.restapi.familym;
import java.util.List;

import org.springframework.data.repository.CrudRepository;

public interface FamilyMRepository extends CrudRepository<FamilyM,String> {

	public List<FamilyM> findByFamilyId(String familyId);
}

