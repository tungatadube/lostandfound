package com.demo.lostandfound.service.controller;

import com.demo.lostandfound.service.model.Media;
import com.demo.lostandfound.service.service.MediaService;
import com.demo.lostandfound.service.utils.payload.UploadFileResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.ArrayList;
import java.util.List;


@RestController
public class MediaController {

	private static final Logger logger = LoggerFactory.getLogger(MediaController.class);



	private MediaService mediaService;
	private MessageSource messageSource;

	@Autowired
	public MediaController(MessageSource messageSource, MediaService mediaService) {
		this.messageSource = messageSource;
		this.mediaService = mediaService;
	}

	@GetMapping("/media")
	public ResponseEntity<List<String>> listAllMedia() {
		List <String> allMedia = new ArrayList<>();
		mediaService.listAllMedia().stream().map(x->x.getFileName()).forEach(allMedia :: add);
		return ResponseEntity.ok(allMedia);


	}

	@GetMapping("/media/{id}")
	public ResponseEntity<Resource> getMedia(@PathVariable("id") Long id) {
		// Load Media from the database
		Media mediaFile = mediaService.getMedia(id);

		// return a response entity whose body contains the byte array to display the media

		return ResponseEntity.ok()
				.contentType(MediaType.parseMediaType(mediaFile.getFileType()))
				.header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\""+ mediaFile.getFileName() + "\"")
				.body(new ByteArrayResource(mediaFile.getData()));
	}

	@PostMapping("/media")
	// This post mapping populates a form filed with the requisite id that will be used by the pestcontroller to attach
	// an image to the pet entity for persistence
	public UploadFileResponse addMedia(@RequestParam("file") MultipartFile file) {
		Media mediaFile = mediaService.addMedia(file);

		return new UploadFileResponse(mediaFile.getId());
	}

	@PutMapping("/media/{id}")
	public ResponseEntity<MultipartFile> editMedia(@PathVariable("id") Long id, @RequestParam("file") MultipartFile file) {
		addMedia(file);
			return ResponseEntity.ok()
					.contentType(MediaType.parseMediaType(file.getContentType()))
					.header(HttpHeaders.CONTENT_DISPOSITION, "Saving file: "+ file.getOriginalFilename())
					.body(file);

		}


	@DeleteMapping("/media/{id}")
	public void deleteMedia(@PathVariable("id") Long id) {
			mediaService.deleteMedia(id);


	}
}