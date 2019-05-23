package com.demo.lostandfound.service.service;

import com.demo.lostandfound.service.model.Pet;
import com.demo.lostandfound.service.repository.PetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class PetServiceImpl implements PetService {

@Autowired
	private PetRepository petRepository;



	@Override
	public List<Pet> listAllPets() {
		List<Pet> petList = new ArrayList<>();
		petRepository.findAll().forEach(petList :: add);
		return petList;
	}

	@Override
	public Optional<Pet> getPet(Long id) {
		return petRepository.findById(id);
	}

	@Override
	public Pet addPet(Pet pet) {
		return petRepository.save(pet);}

	@Override
	public Pet editPet(Pet pet) {return petRepository.save(pet);
	}

	@Override
	public void deletePet(Long id) {
		petRepository.deleteById(id);
	}

	@Override
	public Optional<Pet> findById(Long id) {
		return petRepository.findById(id);
	}

	@Override
	public Pet findByPetIdAndMediaId(Long petId, Long mediaId) {
		return petRepository.findByIdAndMediaId(petId, mediaId);
	}
}
