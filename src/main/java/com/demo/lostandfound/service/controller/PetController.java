package com.demo.lostandfound.service.controller;

import com.demo.lostandfound.service.exception.EntryNotCreatedException;
import com.demo.lostandfound.service.exception.EntryNotDeletedException;
import com.demo.lostandfound.service.exception.EntryNotEditedException;
import com.demo.lostandfound.service.exception.EntryNotFoundException;
import com.demo.lostandfound.service.model.Pet;
import com.demo.lostandfound.service.service.MediaService;
import com.demo.lostandfound.service.service.PetService;
import com.demo.lostandfound.service.utils.mapping.pojos.UIPet;
import org.dozer.DozerBeanMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
import java.util.stream.Collectors;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

@RestController
public class PetController {



	private static final Logger LOGGER = LoggerFactory.getLogger(PetController.class);


	private PetService petService;
	private MessageSource messageSource;
	private DozerBeanMapper mapper;
	private MediaService mediaService;

	@Autowired
	public PetController(MessageSource messageSource, MediaService mediaService, PetService petService,
	                     DozerBeanMapper mapper) {
		this.messageSource = messageSource;
		this.mediaService= mediaService;
		this.petService = petService;
		this.mapper = mapper;
	}

	@GetMapping("/pets")
	public ResponseEntity<List<UIPet>>listAllPets() {
		List<Pet> pets = petService.listAllPets();
		List<UIPet> uiPetList = pets.stream().map(pet -> mapper.map(pet, UIPet.class)).collect(Collectors.toList());
		return ResponseEntity.ok(uiPetList);

	}

	@GetMapping("/pets/pet/{id}")
	public Resource<UIPet> getPet(@PathVariable("id") Long id) {
		if (petService.getPet(id).isPresent()) {

			Pet pet = petService.getPet(id).orElse(null); // will never be null because the pet is present
			
			UIPet uiPet = mapper.map(pet, UIPet.class);

			Resource<UIPet> resource = new Resource<>(uiPet);

			ControllerLinkBuilder linkTo = linkTo(methodOn(this.getClass()).listAllPets());
			resource.add(linkTo.withRel("All-Pets"));


			return resource;
		}
		else{
			String message = messageSource.getMessage("pet.not.found", null, LocaleContextHolder.getLocale());
			throw new EntryNotFoundException(MessageFormat.format(message, id));
		}
	}

	@PostMapping("/pets")
	public ResponseEntity<UIPet> createPet(@RequestBody UIPet uiPet) {
		try{
			LOGGER.info("+++== Unmapped UIPet {} ", uiPet);
			Pet pet = mapper.map(uiPet, Pet.class);
			Pet savedPet = petService.addPet(pet);
			URI location =
				ServletUriComponentsBuilder.fromCurrentRequest().path("/pet/{id}").buildAndExpand(pet.getId()).toUri();
			UIPet responseBody = mapper.map(savedPet, UIPet.class);
		return ResponseEntity.created(location).body(responseBody);}
		catch(Exception e){
			String message = messageSource.getMessage("pet.not.created", null, LocaleContextHolder.getLocale());
			throw new EntryNotCreatedException(MessageFormat.format(message, uiPet.getId()));
		}
	}

	@PutMapping("/pets/pet/{id}")
	public ResponseEntity<Pet> editPet(@PathVariable("id") Long id, @RequestBody Pet pet) {
		if(petService.getPet(id).isPresent()){
			try {
					petService.editPet(pet);
					return ResponseEntity.ok().build();

			} catch (Exception e) {
				String message = messageSource.getMessage("pet.not.edited", null, LocaleContextHolder.getLocale());
				throw new EntryNotEditedException(MessageFormat.format(message, pet.getId()));
			}

		}
		else{
			String message = messageSource.getMessage("pet.not.found", null, LocaleContextHolder.getLocale());
			throw new EntryNotFoundException(MessageFormat.format(message, pet.getId()));
		}
	}

	@DeleteMapping("/pets/pet/{id}")
	public void deletePet(@PathVariable("id") Long id) {
		if(petService.getPet(id).isPresent()) {
			petService.deletePet(id);
		}
		else{
			throw new EntryNotDeletedException(String.format("Cannot delete pet with id-%s", id));
		}
	}
}