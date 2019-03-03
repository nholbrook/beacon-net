package com.nickholbrook.beaconnet.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class EntryExceptionController {
	@ExceptionHandler(value = EntryNotfoundException.class)
	public ResponseEntity<Object> exception(EntryNotfoundException exception) {
		return new ResponseEntity<>("Entry not found", HttpStatus.NOT_FOUND);
	}
}