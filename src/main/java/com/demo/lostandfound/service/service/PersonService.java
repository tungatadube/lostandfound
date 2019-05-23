package com.demo.lostandfound.service.service;

import com.demo.lostandfound.service.model.Person;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public interface PersonService {



	List<Person> listAllPersons();

	Optional<Person> getPerson(Long id);

	void addPerson(Person person);

	void editPerson(Person person);

	void deletePerson(Long id);

}
