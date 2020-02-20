package com.tp.challenge.restapi.family;

import java.io.Serializable;

//import java.io.Serializable;
//import java.util.Set;
//import java.util.stream.Collectors;
//import java.util.stream.Stream;

//import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
//import javax.persistence.OneToMany;
import javax.validation.constraints.Size;

//import com.tp.challenge.restapi.familym.FamilyM;

//import java.util.TreeMap;
//import com.tp.challenge.restapi.familym.FamilyM;
//import java.util.Iterator;
//import java.util.Map;
//import java.util.Set;
//import java.lang.reflect.Field;
//import javax.validation.constraints.NotNull;

@Entity
public class Family implements Serializable{
	//private static long clsID;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;	
	
	@Column(unique = true)
	private String name;	
	
	@Column(name = "countrycode")
	@Size(min = 1, max = 2, message = "Countrycode cannot be Empty and no longer than 2 characters (ISO 3166-1)")
	private String countrycode;
		
	//@OneToMany(mappedBy = "Family", cascade = CascadeType.ALL)
    //private Set<FamilyM> familyms;
	
	
	public Family() {
		 //this.id = Long.toString(clsID++);
	}	
	
	
	public Family(long id, String name, String countrycode) {		
		super();
		//this.id = Long.toString(clsID++);
		this.id = id;
		this.name = name;
		this.countrycode = countrycode;		
		//this.familyms = Stream.of(familyms).collect(Collectors.toSet());
        //this.familyms.forEach(x -> x.setFamily(this));
	}	 	
    public long getid() {
		return this.id;
	}
	public void setFid(long id) {
		 this.id = id;
		}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getCountrycode() {
		return countrycode;
	}
	public void setCountrycode(String countrycode) {
		this.countrycode = countrycode;
	}

	
	public void Merge(Family newObject) {
		if(newObject == null || this.equals(newObject)){
	        return;
	    }		
		//this.id = isEmptyString(newObject.id) ? this.id : newObject.id; 
		this.name = isEmptyString(newObject.name) ? this.name : newObject.name;  
		this.countrycode = isEmptyString(newObject.countrycode) ? this.countrycode : newObject.countrycode;		
	}	
	
	private boolean isEmptyString(String string) {
	    return string == null || string.isEmpty();
	}
	


}
