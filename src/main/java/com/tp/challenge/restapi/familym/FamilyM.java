package com.tp.challenge.restapi.familym;

import java.time.LocalDate;
//import java.time.LocalDateTime;
import java.time.Period;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.tp.challenge.restapi.family.Family;

@Entity
public class FamilyM {
	//private String FId; //family id
	//private static int _ID;
	
	@Id
	private String id;
	
	
		
	@NotNull(message = "First Name is a required field")
	@Size(min = 4, max = 50)
	private String firstname;
	
	@NotNull(message = "Last Name is a required field")
	@Size(min = 4, max = 50)
	private String lastname;
	
	private String fatherId;
	private String motherId;
	
	//@Override
	@JsonFormat(pattern="dd-MM-yyyy")
	@NotNull
	@Past
	private LocalDate dateofbirth;
	
	@ManyToOne
	private Family family;
	
	public Family getFamily() {
		return family;
	}

	public void setFamily(Family family) {
		this.family = family;
	}

	public FamilyM(String id,String firstname, String lastname,String fatherId, String motherId ,LocalDate dateofbirth,String familyid)
    {
		//this.FId = fid;
		//this.id = String.valueOf(_ID++);
		this.id = id;
		this.fatherId = fatherId;
        this.motherId = motherId;
        this.firstname = firstname;
        this.lastname = lastname;
        this.dateofbirth = dateofbirth;
        this.family = new Family(familyid,lastname,"pt");
    }
	
	public FamilyM() {
		 //this.id = String.valueOf(_ID++);
	}	
	
	public String getId() {
		return id;
	}

	public void setId(String Id) {
		this.id = Id;
	}
	
	/*public int get_ID() {
		return _ID;
	}
	
	public String getFId() {
		return FId;
	}

	public void setFId(String FId) {
		this.FId = FId;
	}
	
	
	*/
	
	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	
	public String getFatherId() {
		return fatherId;
	}

	public void setFatherId(String fatherId) {
		this.fatherId = fatherId;
	}

	public String getMotherId() {
		return motherId;
	}

	public void setMotherId(String motherId) {
		this.motherId = motherId;
	}

	public LocalDate getDateofbirth() {
		return dateofbirth;
	}

	public void setDateofbirth(LocalDate dateofbirth) {
		this.dateofbirth = dateofbirth;
	}


	
	public void Merge(FamilyM newObject) {
		if(newObject == null || this.equals(newObject)){
	        return;
	    }		
		//this.id = isEmptyString(newObject.id) ? this.id : newObject.id;
		//this.FId = isEmptyString(newObject.FId) ? this.FId : newObject.FId;
		this.dateofbirth = newObject.dateofbirth==null ? this.dateofbirth : newObject.dateofbirth;  
		this.fatherId = isEmptyString(newObject.fatherId) ? this.fatherId : newObject.fatherId;		
		this.motherId = isEmptyString(newObject.motherId) ? this.motherId : newObject.motherId;		
		this.firstname = isEmptyString(newObject.firstname) ? this.firstname : newObject.firstname;
		this.lastname = isEmptyString(newObject.lastname) ? this.lastname : newObject.lastname;		
	}	
	
	private boolean isEmptyString(String string) {
	    return string == null || string.isEmpty();
	}
	
	
	
	 public double getAge() { 
		 LocalDate today = LocalDate.now();
		 //LocalDate birthday = LocalDate.of(1960, Month.JANUARY, 1);//Birth date
		 Period p = Period.between(dateofbirth, today);
		 return p.getYears();
    }

     public int getBirthYear()
     {
         return dateofbirth.getYear();
     }


}
