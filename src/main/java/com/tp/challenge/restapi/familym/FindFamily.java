package com.tp.challenge.restapi.familym;

public class FindFamily {
	
	private String id;	
	private double result;
	
	FindFamily(String id,double result){
		this.id= id;
		this.result = result;
	}
	
	public double getResult() {
		return result;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public void setResult(double result) {
		this.result = result;
	}

}
