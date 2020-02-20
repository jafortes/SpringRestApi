package com.tp.challenge.restapi.family;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Size;


@Entity
public class Family implements Serializable{
	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;	
	
	@Column(unique = true)
	private String name;	
	
	@Column(name = "countrycode")
	@Size(min = 1, max = 2, message = "Countrycode cannot be Empty and no longer than 2 characters (ISO 3166-1)")
	private String countrycode;
		
	
	
	public Family() {
		 //this.id = Long.toString(clsID++);
	}	
	
	
	public Family(long id, String name, String countrycode) {		
		super();
		this.id = id;
		this.name = name;
		this.countrycode = countrycode;		
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
		this.name = isEmptyString(newObject.name) ? this.name : newObject.name;  
		this.countrycode = isEmptyString(newObject.countrycode) ? this.countrycode : newObject.countrycode;		
	}	
	
	private boolean isEmptyString(String string) {
	    return string == null || string.isEmpty();
	}
	

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Family other = (Family) obj;
		if (id == 0) {
			if (other.id != 0)
				return false;
		} else if (id != other.id)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return String.format(
				"Family [id=%s, name=%s, countrycode=%s]", id,
				name, countrycode);
	}
	
}
