package com.demo.lostandfound.service.repository;

import com.demo.lostandfound.service.model.Media;
import com.demo.lostandfound.service.model.Pet;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface MediaRepository extends PagingAndSortingRepository<Media, Long> {

}

