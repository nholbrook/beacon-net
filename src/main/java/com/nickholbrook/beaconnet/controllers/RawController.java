package com.nickholbrook.beaconnet.controllers;

import java.util.List;

import com.nickholbrook.beaconnet.model.Entry;
import com.nickholbrook.beaconnet.service.DynamoDBAuthService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/raw")
public class RawController extends EntryControllerBase{

	@RequestMapping(method = RequestMethod.GET)
	public String getRaw(Model model) {
		List<Entry> entryList = getEntryTableService().getEntries();
		if (entryList != null && entryList.size() > 0) {
			model.addAttribute(ENTRY_LIST, entryList);
		}
		return "raw";
	}

}
