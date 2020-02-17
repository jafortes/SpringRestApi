package com.tp.challenge.restapi.family;

//import java.lang.reflect.Field;
//import java.time.LocalDate;

//import javax.validation.constraints.NotNull;
//import javax.validation.constraints.Past;
import javax.validation.constraints.Size;

public class Family {
	private String id;
	private static long clsID;
	
	//String s2 = Long.toString(big);    
    //String s3 = String.valueOf(big);
	
	//@NotNull(message = "Name is a required field")
	private String name;
	@Size(min = 1, max = 2)
	
	@Size(min = 1, max = 2, message = "Countrycode cannot be Empty and no longer than 2 characters (ISO 3166-1)")
	private String countrycode;
		
	//@NotNull
	//@Past
	//private LocalDate dateOfBirth;
	
	
	public Family() {
		 this.id = Long.toString(clsID++);
	}	
	
	public Family(String name, String countrycode) {		
		super();
		this.id = Long.toString(clsID++);		
		this.name = name;
		this.countrycode = countrycode;
	}	 	
    public String getId() {
		return id;
	}
	/*public void setId(String id) {
		 this.id = id;
		}*/
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
