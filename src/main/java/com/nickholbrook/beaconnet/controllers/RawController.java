package com.nickholbrook.beaconnet.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/raw")
public class RawController extends EntryControllerBase {

	@RequestMapping(method = RequestMethod.GET)
	public String getRaw(Model model) {
		return "raw";
	}

}
