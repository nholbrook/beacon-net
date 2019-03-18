package com.nickholbrook.beaconnet.controllers;

import com.nickholbrook.beaconnet.service.BeaconTableService;

public abstract class BeaconControllerBase {
	private final static BeaconTableService beaconTableService = new BeaconTableService();
	protected static final String BEACON_LIST = "beaconList";

	protected BeaconTableService getBeaconTableService() {
		return beaconTableService;
	}
}
