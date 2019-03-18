package com.nickholbrook.beaconnet.controllers;

import java.util.List;

import com.nickholbrook.beaconnet.model.Beacon;
import com.nickholbrook.beaconnet.model.Entry;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/")
public class IndexController extends EntryControllerBase {

	@RequestMapping(method = RequestMethod.GET)
	public String getIndex(Model model) {
		List<Entry> entryList = getEntryTableService().getEntries();
		if (entryList != null && entryList.size() > 0) {
			model.addAttribute(ENTRY_LIST, entryList);
		}
		System.out.println(model);
		return "index";
	}

}
