package com.nickholbrook.beaconnet.controllers;

import java.util.List;

import com.nickholbrook.beaconnet.model.Summary;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/")
public class IndexController extends SummaryControllerBase {

	@RequestMapping(method = RequestMethod.GET)
	public String getIndex(Model model) {
		List<Summary> summaryList = getSummaryTableService().getSummaries();
		if (summaryList != null && summaryList.size() > 0) {
			model.addAttribute(SUMMARY_LIST, summaryList);
		}
		return "index";
	}

}
