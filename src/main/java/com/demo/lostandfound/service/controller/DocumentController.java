package com.demo.lostandfound.service.controller;

import com.demo.lostandfound.service.exception.EntryNotEditedException;
import com.demo.lostandfound.service.model.Document;
import com.demo.lostandfound.service.exception.EntryNotCreatedException;
import com.demo.lostandfound.service.exception.EntryNotDeletedException;
import com.demo.lostandfound.service.exception.EntryNotFoundException;
import com.demo.lostandfound.service.service.DocumentService;
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
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.*;

@RestController
public class DocumentController {



	private DocumentService documentService;
	private MessageSource messageSource;

	@Autowired
	public DocumentController(MessageSource messageSource, DocumentService documentService) {
		this.messageSource = messageSource;
		this.documentService = documentService;
	}

	@GetMapping("/documents")
	public List<Document> listAllDocuments() {
		return documentService.listAllDocuments();
	}

	@GetMapping("/documents/document/{id}")
	public Resource<Document> getDocument(@PathVariable("id") Long id) {
		if (documentService.getDocument(id).isPresent()) {

			Document document = documentService.getDocument(id).orElse(null);

			Resource<Document> resource = new Resource<>(document);

			ControllerLinkBuilder linkTo = linkTo(methodOn(this.getClass()).listAllDocuments());
			resource.add(linkTo.withRel("All-Agents"));


			return resource;
		}
		else{
			String message = messageSource.getMessage("Document.not.found", null, LocaleContextHolder.getLocale());
			throw new EntryNotFoundException(MessageFormat.format(message, id));
		}
	}

	@PostMapping("/documents")
	public ResponseEntity<Document> addDocument(@RequestBody Document document) {
		try{
			documentService.addDocument(document);
			URI location =
				ServletUriComponentsBuilder.fromCurrentRequest().path("/document/{id}").buildAndExpand(document.getId()).toUri();
		return ResponseEntity.created(location).build();}
		catch(Exception e){
			String message = messageSource.getMessage("Document.not.added", null, LocaleContextHolder.getLocale());
			throw new EntryNotCreatedException(MessageFormat.format(message, document.getId()));
		}
	}

	@PutMapping("/documents/document/{id}")
	public ResponseEntity<Document> editDocument(@PathVariable("id") Long id, @RequestBody Document document) {
		if(documentService.getDocument(id).isPresent()){
			try {
					documentService.editDocument(document);
					return ResponseEntity.ok().build();

			} catch (Exception e) {
				String message = messageSource.getMessage("Document.not.edited", null, LocaleContextHolder.getLocale());
				throw new EntryNotEditedException(MessageFormat.format(message, document.getId()));
			}

		}
		else{
			String message = messageSource.getMessage("Document.not.found", null, LocaleContextHolder.getLocale());
			throw new EntryNotFoundException(MessageFormat.format(message, document.getId()));
		}
	}

	@DeleteMapping("/documents/document/{id}")
	public void deleteDocument(@PathVariable("id") Long id) {
		if(documentService.getDocument(id).isPresent()) {
			documentService.deleteDocument(id);
		}
		else{
			throw new EntryNotDeletedException(String.format("Cannot delete document with id-%s", id));
		}
	}
}