package com.demo.lostandfound.service.service;

import com.demo.lostandfound.service.model.Document;
import com.demo.lostandfound.service.repository.DocumentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class DocumentServiceImpl implements DocumentService {


	private DocumentRepository documentRepository;

	@Autowired
	public DocumentServiceImpl(DocumentRepository documentRepository) {
		this.documentRepository = documentRepository;
	}

	@Override
	public List<Document> listAllDocuments() {
		List<Document> agentList = new ArrayList<>();
		documentRepository.findAll().forEach(agentList :: add);
		return agentList;
	}

	@Override
	public Optional<Document> getDocument(Long id) {
		return documentRepository.findById(id);
	}

	@Override
	public void addDocument(Document document) {
		 documentRepository.save(document);
	}

	@Override
	public void editDocument(Document document) {
		documentRepository.save(document);
	}

	@Override
	public void deleteDocument(Long id) {
		documentRepository.deleteById(id);
	}
}
