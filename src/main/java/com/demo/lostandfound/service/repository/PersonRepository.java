package com.demo.lostandfound.service.repository;

import com.demo.lostandfound.service.model.Person;
import org.springframework.data.repository.CrudRepository;

public interface PersonRepository extends CrudRepository<Person, Long> {

}

