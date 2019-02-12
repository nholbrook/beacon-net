package com.nickholbrook.beaconnet.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/home")
public class HomeController {

	// If not authenticated

	/*
	@RequestMapping(method = RequestMethod.GET)
	public String getMethod() {
		return "redirect:login";
	}
	*/

	//If authenticated
	@RequestMapping(method = RequestMethod.GET)
	public String getHome() {
		return "index";
	}
}
