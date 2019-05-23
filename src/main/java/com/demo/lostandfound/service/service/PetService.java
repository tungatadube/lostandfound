package com.demo.lostandfound.service.service;

import com.demo.lostandfound.service.model.Pet;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public interface PetService {



	List<Pet> listAllPets();

	Optional<Pet> getPet(Long id);

	Pet addPet(Pet pet);

	Pet editPet(Pet pet);

	void deletePet(Long id);
	Optional<Pet> findById(Long id);
	Pet findByPetIdAndMediaId(Long mediaId, Long petId);


}
