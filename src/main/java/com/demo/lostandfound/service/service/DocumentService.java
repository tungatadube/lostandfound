package com.demo.lostandfound.service.service;

import com.demo.lostandfound.service.model.Document;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public interface DocumentService {



	List<Document> listAllDocuments();

	Optional<Document> getDocument(Long id);

	void addDocument(Document document);

	void editDocument(Document document);

	void deleteDocument(Long id);

}
