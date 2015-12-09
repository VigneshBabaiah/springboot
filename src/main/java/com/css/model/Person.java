package com.css.model;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonRootName("person")
public class Person {

	@JsonProperty("id")
	@NotNull
	@Min(1)
	private long	id;
	
	@JsonProperty("name")
	@NotNull
	@Size(min = 1, max = 16)
	private String 	name;
	
	public Person() {
		
	}
	
	public Person(String  name) {
		this.name = name;
	}
	
	public long getId() {
		return id;
	}
	
	public void setId(long id) {
		this.id = id;
	}
	
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "Person: ID = " + this.id + ", NAME =  " + this.name;
	}
	
}
