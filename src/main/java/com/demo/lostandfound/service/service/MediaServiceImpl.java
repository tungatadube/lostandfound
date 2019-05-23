package com.demo.lostandfound.service.service;

import com.demo.lostandfound.service.exception.FileNotFoundException;
import com.demo.lostandfound.service.exception.FileStorageException;
import com.demo.lostandfound.service.model.Media;
import com.demo.lostandfound.service.repository.MediaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class MediaServiceImpl implements MediaService {

	@Autowired
	private MediaRepository mediaRepository;


	@Override
	public List<Media> listAllMedia() {
		List<Media> mediaList = new ArrayList<>();
		mediaRepository.findAll().forEach(mediaList :: add);
		return mediaList;
	}

	@Override
	public Media getMedia(Long id) {
		return mediaRepository.findById(id).orElseThrow(() ->new FileNotFoundException("File not found with id " + id));
	}

	@Override
	public Media addMedia(MultipartFile file) {

// Normalize the file
		String fileName = StringUtils.cleanPath(file.getOriginalFilename());

		try{
			// Check if the file contains invalid characters

			if(fileName.contains("..")){
				throw new FileStorageException("Sorry your file name contains invalid characters");
			}

			Media media = new Media(fileName, file.getContentType(), file.getBytes());
			return mediaRepository.save(media);
		}catch (IOException e){throw new FileStorageException("Could not store file "+ fileName);}


	}

	@Override
	public void editMedia(Media media) {
		mediaRepository.save(media);
	}

	@Override
	public void deleteMedia(Long id) {
		mediaRepository.deleteById(id);
	}

	@Override
	public Optional<Media> findById(Long id) {
		return mediaRepository.findById(id);
	}
}
