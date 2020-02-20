package com.tp.challenge.restapi.family;

//import java.util.Map;


//import com.fasterxml.jackson.annotation.JsonAnySetter;

//import java.lang.reflect.Field;
//import java.time.LocalDate;

//import javax.validation.constraints.NotNull;
//import javax.validation.constraints.Past;
//import javax.validation.constraints.Size;


/*public class CountryCode {
    public String desc;
    public String code;
    
    private Map<String, String> properties;
 
    @JsonAnySetter
    public void add(String key, String value) {
        properties.put(key, value);
    }
}*/


public class CountryCode {

	private String name;	
	private String alpha2;
	private String countrycode;
	
	
	
	
	public CountryCode() {
		
	}		
	public CountryCode(String name,String alpha2, String countrycode) {
		super();
		this.name=name;
		this.alpha2 = alpha2;
		this.countrycode = countrycode;
	}

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAlpha2() {
		return alpha2;
	}
	public void setAlpha2(String alpha2) {
		this.alpha2 = alpha2;
	}
	
	public String getCountrycode() {
		return countrycode;
	}
	public void setCountrycode(String countrycode) {
		this.countrycode = countrycode;
	}

	@Override
    public String toString() {
        return "{" +
                "name='" + name + '\'' +
                ", alpha2='" + alpha2 + '\'' +                
                ", countrycode='" + countrycode + '\'' +
                '}';
    }
	
}
