package com.css.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.css.model.Customer;
import com.css.repository.CustomerRepository;

@Service
public class CustomerService {
	
	@Autowired
	private CustomerRepository customerRepository;
	
	public CustomerService() {
				
	}
	
	public Customer createCustomer(Customer customer) { 
		return customerRepository.createCustomer(customer); 
	} 
	
	public Customer getCustomer(int id) { 
		return customerRepository.getCustomer(id); 
	} 
	
	public Customer updateCustomer(Customer customer) { 
		return customerRepository.updateCustomer(customer); 
	} 
	
	public void deleteCustomer(int id) { 
		customerRepository.deleteCustomer(id); 
	} 
	
	public List<Customer> getAllCustomers() { 
		return customerRepository.getAllCustomers(); 
	}
}
