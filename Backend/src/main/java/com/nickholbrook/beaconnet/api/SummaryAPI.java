package com.nickholbrook.beaconnet.api;

import java.util.List;

import com.nickholbrook.beaconnet.model.Summary;
import com.nickholbrook.beaconnet.service.SummaryTableService;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SummaryAPI {
	private final static SummaryTableService summaryTableService = new SummaryTableService();
	protected static final String SUMMARY_LIST = "summaryList";

	protected SummaryTableService getSummaryTableService() {
		return summaryTableService;
	}

	@CrossOrigin
	@RequestMapping(value = "/summaries", method = RequestMethod.GET)
	@ResponseBody
	public List<Summary> getAllSummaries() {
		List<Summary> summaryList = getSummaryTableService().getSummaries();
		return summaryList;
	}

	@CrossOrigin
	@RequestMapping(value = "/summariesRange", method = RequestMethod.GET)
	@ResponseBody
	public List<Summary> getSummariesRange(@RequestParam("startTime") String startTime, @RequestParam("endTime") String endTime) {
		List<Summary> summaryList = getSummaryTableService().getSummariesRange(startTime, endTime);
		return summaryList;
	}

}
