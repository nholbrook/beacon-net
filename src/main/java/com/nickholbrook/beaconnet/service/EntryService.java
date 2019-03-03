package com.nickholbrook.beaconnet.service;

import java.util.HashMap;
import java.util.Map;

import com.nickholbrook.beaconnet.exception.EntryNotfoundException;
import com.nickholbrook.beaconnet.model.Entry;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Service
public class EntryService {
	private static Map<String, Entry> entrySet = new HashMap<String, Entry>();

	static {
		Entry e1 = new Entry();
		e1.setFirstTime("Mon Jan 28 23:00:19 2019");
		e1.setLastTime("Mon Jan 28 23:00:22 2019\n");
		e1.setMacAddress("15:05:5E:1E:FB:3D");
		e1.setManufacturer("Sony");
		entrySet.put("15:05:5E:1E:FB:3D", e1);

		Entry e2 = new Entry();
		e2.setFirstTime("Mon Jan 28 23:00:21 2019");
		e2.setLastTime("Mon Jan 28 23:00:23 2019\n");
		e2.setMacAddress("16:05:5E:1E:FB:3D");
		e2.setManufacturer("Mitshibushi");
		entrySet.put("16:05:5E:1E:FB:3D", e2);

		Entry e3 = new Entry();
		e3.setFirstTime("Mon Jan 28 23:00:22 2019");
		e3.setLastTime("Mon Jan 28 23:00:25 2019\n");
		e3.setMacAddress("17:05:5E:1E:FB:3D");
		e3.setManufacturer("Toyota");
		entrySet.put("17:05:5E:1E:FB:3D", e3);
	}

	public ResponseEntity<Object> deleteEntry(@PathVariable("id") String id) {
		if(!entrySet.containsKey(id))throw new EntryNotfoundException();
		entrySet.remove(id);
		return new ResponseEntity<>("Entity is deleted successfully", HttpStatus.OK);
	}

	public ResponseEntity<Object> updateEntry(@PathVariable("id") String id, @RequestBody Entry entry) {
		if(!entrySet.containsKey(id))throw new EntryNotfoundException();
		entrySet.remove(id);
		entry.setMacAddress(id);
		entrySet.put(id, entry);
		return new ResponseEntity<>("Entity is updated successfully", HttpStatus.OK);
	}

	public ResponseEntity<Object> createEntry(@RequestBody Entry entry) {
		entrySet.put(entry.getMacAddress(), entry);
		return new ResponseEntity<>("Entity is created successfully", HttpStatus.CREATED);
	}

	public ResponseEntity<Object> getEntry(@PathVariable("id") String id) {
		return new ResponseEntity<>(entrySet.getOrDefault(id, null), HttpStatus.OK);
	}

	public ResponseEntity<Object> getAllEntries() {
		return new ResponseEntity<>(entrySet.values(), HttpStatus.OK);
	}
}
