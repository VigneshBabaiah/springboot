package com.css.model;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

import org.springframework.data.cassandra.mapping.Column; 
import org.springframework.data.cassandra.mapping.PrimaryKey; 
import org.springframework.data.cassandra.mapping.Table;

@Table("customer")
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonRootName("customer")
public class Customer {

	@JsonProperty("id")
	@NotNull
	@Min(1)
	@PrimaryKey("id")
	private long	id;
	
	@JsonProperty("name")
	@NotNull
	@Column("name")
	private String 	name;
	
	@JsonProperty("level")
	@NotNull
	@Column("level")
	private String 	level;
	

	public Customer() {
		
	}
	
	public Customer(String  name) {
		this.name = name;
	}
	
	public Customer(long id, String  name, String level) {
		this.name = name;
		this.id = id;
		this.level = level;
	}
	
	public Customer(String id, String  name, String level) {
		this.name = name;
		this.id = Long.parseLong(id);
		this.level = level;
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

	public String getLevel() {
		return level;
	}

	public void setLevel(String level) {
		this.level = level;
	}

	@Override
	public String toString() {
		return "Customer: ID = " + this.id + ", NAME =  " + this.name;
	}
	
}
