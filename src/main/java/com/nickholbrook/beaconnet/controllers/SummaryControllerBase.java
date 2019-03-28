package com.nickholbrook.beaconnet.controllers;

import com.nickholbrook.beaconnet.service.SummaryTableService;

public abstract class SummaryControllerBase {
	private final static SummaryTableService summaryTableService = new SummaryTableService();
	protected static final String SUMMARY_LIST = "summaryList";

	protected SummaryTableService getSummaryTableService() {
		return summaryTableService;
	}
}
