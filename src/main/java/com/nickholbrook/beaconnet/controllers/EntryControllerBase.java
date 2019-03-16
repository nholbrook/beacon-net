package com.nickholbrook.beaconnet.controllers;

import com.nickholbrook.beaconnet.service.EntryTableService;

public abstract class EntryControllerBase {
	private final static EntryTableService entryTableService = new EntryTableService();
	protected static final String ENTRY_LIST = "entryList";

	protected EntryTableService getEntryTableService() {
		return entryTableService;
	}
}
