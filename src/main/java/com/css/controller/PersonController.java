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

import com.css.model.Person;
import com.css.service.PersonService;

@RestController
@RequestMapping("/person")
public class PersonController {
	
	@Autowired
	private PersonService personService;
	
	@RequestMapping(value="/all", method=RequestMethod.GET)
	public List<Person> getAll() {
		// NOT HAL
		return personService.getAllPersons();		
	}	
		
	@RequestMapping(value="/{id}", method=RequestMethod.GET, produces = "application/json")
	@ResponseBody
	public HttpEntity<Resource<Person>> person(
            @PathVariable("id") String id) {
		
		// IS HAL
		Resource<Person> person = new Resource<>(personService.getPerson(id));
        person.add(linkTo(methodOn(PersonController.class).person(id)).withSelfRel());

        return new ResponseEntity<>(person, HttpStatus.OK);
    }
	
	// Notice that we use @RequestBody now. To use this, we need to put something like the following
	// in the body of the request
	// {
	//  "id": 7,
	//  "name": "Vedang"
	//}
	// Now the server will not create the object if you don't supply name and will tell you it needs name.
	// Without the @Validate, it would have simply created the object without a name
	@RequestMapping(method=RequestMethod.POST, produces = "application/json")
	public void createPerson( @Validated @RequestBody Person person) {
		personService.createPerson(person.getId(), person.getName());
	}

}
