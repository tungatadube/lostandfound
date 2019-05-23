package com.demo.lostandfound.service.repository;

import com.demo.lostandfound.service.model.Document;
import org.springframework.data.repository.CrudRepository;

public interface DocumentRepository extends CrudRepository<Document, Long> {

}
