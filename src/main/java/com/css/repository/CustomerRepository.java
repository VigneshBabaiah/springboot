package com.css.repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.jdbc.core.RowMapper;

import com.css.model.Customer;
import com.css.util.CustomCassandraTemplate;

@Repository
public class CustomerRepository {
	
	@Autowired 
	private CustomCassandraTemplate customCassandraTemplate; 
	
	 
	public Customer createCustomer(Customer customer) { 
		return customCassandraTemplate.create(customer); 
	} 
	
	 
	public Customer getCustomer(int id) { 
		return customCassandraTemplate.findById(id, Customer.class); 
	} 
	
	 
	public Customer updateCustomer(Customer customer) { 
		return customCassandraTemplate.update(customer, Customer.class); 
	} 
	
	 
	public void deleteCustomer(int id) { 
		customCassandraTemplate.deleteById(id, Customer.class); 
	} 
	
	 
	public List<Customer> getAllCustomers() { 
		return customCassandraTemplate.findAll(Customer.class); 
	}	

}