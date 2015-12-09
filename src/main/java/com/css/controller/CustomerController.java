package com.css.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import org.springframework.hateoas.Resource;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

import com.css.model.Customer;
import com.css.service.CustomerService;

@RestController
@RequestMapping("/customer")
public class CustomerController {
	
	@Autowired
	private CustomerService customerService;
	
	@RequestMapping(value="/all", method=RequestMethod.GET)
	public List<Customer> getAll() {
		// NOT HAL
		return customerService.getAllCustomers();		
	}	
		
	
	@RequestMapping(value="/{id}", method=RequestMethod.GET, produces = "application/json")
	@ResponseBody
	public HttpEntity<Resource<Customer>> customer(
            @PathVariable("id") int id) {
		
		// IS HAL
		Resource<Customer> customer = new Resource<>(customerService.getCustomer(id));
        customer.add(linkTo(methodOn(CustomerController.class).customer(id)).withSelfRel());

        return new ResponseEntity<>(customer, HttpStatus.OK);
    }
	

	@RequestMapping(method=RequestMethod.POST, produces = "application/json")
	public void createCustomer( @Validated @RequestBody Customer customer) {
		customerService.createCustomer(customer);
	}

}
