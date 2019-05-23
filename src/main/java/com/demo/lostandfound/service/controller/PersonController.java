package com.demo.lostandfound.service.controller;

import com.demo.lostandfound.service.exception.EntryNotCreatedException;
import com.demo.lostandfound.service.exception.EntryNotDeletedException;
import com.demo.lostandfound.service.exception.EntryNotEditedException;
import com.demo.lostandfound.service.exception.EntryNotFoundException;
import com.demo.lostandfound.service.model.Person;
import com.demo.lostandfound.service.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.mvc.ControllerLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.text.MessageFormat;
import java.util.List;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

@RestController
public class PersonController {



	private PersonService personService;
	private MessageSource messageSource;

	@Autowired
	public PersonController(MessageSource messageSource, PersonService personService) {
		this.messageSource = messageSource;
		this.personService = personService;
	}

	@GetMapping("/persons")
	public List<Person> listAllPersons() {
		return personService.listAllPersons();
	}

	@GetMapping("/persons/person/{id}")
	public Resource<Person> getPerson(@PathVariable("id") Long id) {
		if (personService.getPerson(id).isPresent()) {

			Person person = personService.getPerson(id).orElse(null);

			Resource<Person> resource = new Resource<>(person);

			ControllerLinkBuilder linkTo = linkTo(methodOn(this.getClass()).listAllPersons());
			resource.add(linkTo.withRel("All-Agents"));


			return resource;
		}
		else{
			String message = messageSource.getMessage("Person.not.found", null, LocaleContextHolder.getLocale());
			throw new EntryNotFoundException(MessageFormat.format(message, id));
		}
	}

	@PostMapping("/Persons")
	public ResponseEntity<Person> addPerson(@RequestBody Person person) {
		try{
			personService.addPerson(person);
			URI location =
				ServletUriComponentsBuilder.fromCurrentRequest().path("/person/{id}").buildAndExpand(person.getId()).toUri();
		return ResponseEntity.created(location).build();}
		catch(Exception e){
			String message = messageSource.getMessage("Person.not.added", null, LocaleContextHolder.getLocale());
			throw new EntryNotCreatedException(MessageFormat.format(message, person.getId()));
		}
	}

	@PutMapping("/persons/person/{id}")
	public ResponseEntity<Person> editPerson(@PathVariable("id") Long id, @RequestBody Person person) {
		if(personService.getPerson(id).isPresent()){
			try {
					personService.editPerson(person);
					return ResponseEntity.ok().build();

			} catch (Exception e) {
				String message = messageSource.getMessage("Person.not.edited", null, LocaleContextHolder.getLocale());
				throw new EntryNotEditedException(MessageFormat.format(message, person.getId()));
			}

		}
		else{
			String message = messageSource.getMessage("Person.not.found", null, LocaleContextHolder.getLocale());
			throw new EntryNotFoundException(MessageFormat.format(message, person.getId()));
		}
	}

	@DeleteMapping("/persons/person/{id}")
	public void deletePerson(@PathVariable("id") Long id) {
		if(personService.getPerson(id).isPresent()) {
			personService.deletePerson(id);
		}
		else{
			throw new EntryNotDeletedException(String.format("Cannot delete person with id-%s", id));
		}
	}
}