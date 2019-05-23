package com.demo.lostandfound.service.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class FileStorageException extends RuntimeException {

	public FileStorageException(String message) {
		super(message);
	}
}