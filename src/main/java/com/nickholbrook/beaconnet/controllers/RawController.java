package com.nickholbrook.beaconnet.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/raw")
public class RawController {

	@RequestMapping(method = RequestMethod.GET)
	public String getRaw() {
		return "raw";
	}

}
