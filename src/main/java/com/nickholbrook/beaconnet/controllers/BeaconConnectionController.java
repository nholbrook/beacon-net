package com.nickholbrook.beaconnet.controllers;

import java.util.List;

import com.nickholbrook.beaconnet.model.Beacon;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/beacon-connection")
public class BeaconConnectionController extends BeaconControllerBase{

	@RequestMapping(method = RequestMethod.GET)
	public String getBeaconConnection(Model model) {
		List<Beacon> beaconList = getBeaconTableService().getAllBeacons("41d4bac2-cd9f-42dd-852b-003703fc6bca");
		if (beaconList != null && beaconList.size() > 0) {
			model.addAttribute(BEACON_LIST, beaconList);
		}
		System.out.println(model);
		return "beaconconnection";
	}

}