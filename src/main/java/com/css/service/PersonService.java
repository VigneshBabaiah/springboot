package com.css.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.css.model.Person;
import com.css.repository.PersonRepository;

@Service
public class PersonService {
	
	@Autowired
	private PersonRepository personRepository;
	
	public PersonService() {
				
	}
	
	public Person getPerson(String id) { 
		return personRepository.getPerson(Long.parseLong(id));
	}
	
	public List<Person> getAllPersons() {
		return personRepository.getAllPersons();
	}
	
	public Person createPerson(String id, String name) {
		return personRepository.createPerson(Long.parseLong(id), name);
	}
	
	public Person createPerson(long id, String name) {
		return personRepository.createPerson(id, name);
	}
}
