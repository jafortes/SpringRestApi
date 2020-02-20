package com.tp.challenge.restapi.family;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.data.repository.CrudRepository;

//public interface  FamilyRepository extends CrudRepository<Family,Long> {
public interface  FamilyRepository extends JpaRepository<Family,Long> {

	Family findByName(String name);
	List<Family> findByCountrycode(String countrycode);

}
