package com.nickholbrook.beaconnet.controllers;

import com.nickholbrook.beaconnet.exception.EntryNotfoundException;
import com.nickholbrook.beaconnet.model.Entry;
import com.nickholbrook.beaconnet.service.EntryService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EntryServiceController {
	@Autowired
	EntryService entryService;

	@RequestMapping(value = "/api/entry/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Object> delete(@PathVariable("id") String id) {
		return entryService.deleteEntry(id);
	}

	@RequestMapping(value = "/api/entry/{id}", method = RequestMethod.PUT)
	public ResponseEntity<Object> update(@PathVariable("id") String id, @RequestBody Entry entry) {
		return entryService.updateEntry(id, entry);
	}

	@RequestMapping(value = "/api/entry", method = RequestMethod.POST)
	public ResponseEntity<Object> create(@RequestBody Entry entry) {
		return entryService.createEntry(entry);
	}

	@RequestMapping(value = "/api/entry")
	public ResponseEntity<Object> get(@PathVariable("id") String id) {
		return entryService.getEntry(id);
	}


	@RequestMapping(value = "/api/entry")
	public ResponseEntity<Object> get() {
		return entryService.getAllEntries();
	}



}
