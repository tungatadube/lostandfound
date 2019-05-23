package com.demo.lostandfound.service.service;

import com.demo.lostandfound.service.model.Person;
import com.demo.lostandfound.service.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class PersonServiceImpl implements PersonService {


	private PersonRepository personRepository;

	@Autowired
	public PersonServiceImpl(PersonRepository personRepository) {
		this.personRepository = personRepository;
	}

	@Override
	public List<Person> listAllPersons() {
		List<Person> petList = new ArrayList<>();
		personRepository.findAll().forEach(petList :: add);
		return petList;
	}

	@Override
	public Optional<Person> getPerson(Long id) {
		return personRepository.findById(id);
	}

	@Override
	public void addPerson(Person pet) {
		 personRepository.save(pet);
	}

	@Override
	public void editPerson(Person pet) {
		personRepository.save(pet);
	}

	@Override
	public void deletePerson(Long id) {
		personRepository.deleteById(id);
	}
}
