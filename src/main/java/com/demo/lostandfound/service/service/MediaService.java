package com.demo.lostandfound.service.service;

import com.demo.lostandfound.service.model.Media;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;

@Component
public interface MediaService {



	List<Media> listAllMedia();

	Media getMedia(Long id);

	Media addMedia(MultipartFile file);

	void editMedia(Media media);

	void deleteMedia(Long id);

	Optional<Media> findById(Long id);

}
