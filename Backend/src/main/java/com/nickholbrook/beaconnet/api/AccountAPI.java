package com.nickholbrook.beaconnet.api;

import java.util.List;

import com.nickholbrook.beaconnet.model.Beacon;
import com.nickholbrook.beaconnet.model.Entry;
import com.nickholbrook.beaconnet.service.BeaconTableService;
import com.nickholbrook.beaconnet.service.DynamoDBAuthService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AccountAPI {
	private final static BeaconTableService beaconTableService = new BeaconTableService();
	protected static final String BEACON_LIST = "beaconList";
	protected BeaconTableService getBeaconTableService() {
		return beaconTableService;
	}

	@CrossOrigin
	@RequestMapping(value = "/beacons", method = RequestMethod.GET)
	@ResponseBody
	public List<Beacon> getAllBeacons(@RequestParam("accountId") String accountId) {
		List<Beacon> beaconList = getBeaconTableService().getAllBeacons(accountId); //"41d4bac2-cd9f-42dd-852b-003703fc6bca"
		return beaconList;
	}
}
