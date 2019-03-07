package com.nickholbrook.beaconnet.controllers;

import com.nickholbrook.beaconnet.service.EntryService;

abstract class EntryControllerBase {
	private final static String ENTRY_TABLE_NAME = "book_table";
	private final static EntryService entryTableService = new EntryService( ENTRY_TABLE_NAME );
	protected static final String ENTRY_LIST = "bookList";

	protected EntryService getEntryTableService() {
		return entryTableService;
	}
}
