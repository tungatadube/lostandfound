package com.demo.lostandfound.service.repository;

import com.demo.lostandfound.service.model.Pet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

public interface PetRepository extends JpaRepository<Pet, Long> {
	Pet findByIdAndMediaId(Long petId, Long mediaId);

}

