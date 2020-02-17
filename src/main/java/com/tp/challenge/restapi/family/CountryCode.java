package com.tp.challenge.restapi.family;

import java.util.Map;


import com.fasterxml.jackson.annotation.JsonAnySetter;

//import java.lang.reflect.Field;
//import java.time.LocalDate;

//import javax.validation.constraints.NotNull;
//import javax.validation.constraints.Past;
//import javax.validation.constraints.Size;


public class CountryCode {
    public String name;
    private Map<String, String> properties;
 
    @JsonAnySetter
    public void add(String key, String value) {
        properties.put(key, value);
    }
}


/*public class CountryCode {

	private String countrycode;	
	private String description;
		
		
	public CountryCode() {
		
	}		
	public CountryCode(String countrycode, String description) {
		super();
		this.description = description;
		this.countrycode = countrycode;
	}

	public String getDescription() {
		return description;
	}
	public void setSescription(String description) {
		this.description = description;
	}
	public String getCountrycode() {
		return countrycode;
	}
	public void setCountrycode(String countrycode) {
		this.countrycode = countrycode;
	}

}*/
