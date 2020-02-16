package com.tp.challenge.restapi.family;

public class Family {

	//private String id;
	private String name;
	private String countrycode;
	
	
	public Family() {
		
	}	
	public Family(String name, String countrycode) {
		super();
		//this.id = id;
		this.name = name;
		this.countrycode = countrycode;
	}
	//public String getId() {
	//	return id;
	//}
	//public void setId(String id) {
	//	 this.id = id;
	//	}
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

	
}
