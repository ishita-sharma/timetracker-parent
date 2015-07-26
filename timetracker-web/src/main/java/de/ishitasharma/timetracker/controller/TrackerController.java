package de.ishitasharma.timetracker.controller;


import javax.inject.Inject;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import de.ishitasharma.timetracker.model.Tracker;
import de.ishitasharma.timetracker.service.ITrackerService;

@Controller
@RequestMapping("/track")
public class TrackerController {

	@Inject
	private ITrackerService trackerService;

	@RequestMapping(value = "/create/customer", method = { RequestMethod.GET }, produces = { MediaType.APPLICATION_JSON_VALUE })
	@ResponseBody
	public String createCustomer(
			@RequestParam(value = "customerName", required = true) String customerName) {
		return trackerService.createCustomer(customerName);
	}

	@RequestMapping(value = "/start", method = { RequestMethod.GET }, produces = { MediaType.APPLICATION_JSON_VALUE })
	@ResponseBody
	public String startTrack(
			@RequestParam(value = "message", defaultValue = "ok") String message,
			@RequestParam(value = "userName", required = true) String userName,
			@RequestParam(value = "customerName", required = true) String customerName) {
		return trackerService.startTrack(message, userName, customerName).toString();
	}

	@RequestMapping(value = "/status", method = { RequestMethod.GET }, produces = { MediaType.APPLICATION_JSON_VALUE })
	@ResponseBody
	public String status(@RequestParam(required = true) String trackingId) {
		return trackerService.status(trackingId).toString();
	}

	@RequestMapping(value = "/user/history", method = { RequestMethod.GET }, produces = { MediaType.APPLICATION_JSON_VALUE })
	@ResponseBody
	public String userHistory(@RequestParam(required = true) String userName,
			@RequestParam(required = true) String customerName) {
		return trackerService.userHistory(userName, customerName).toString();
	}

	@RequestMapping(value = "/stop", method = { RequestMethod.GET }, produces = { MediaType.APPLICATION_JSON_VALUE })
	@ResponseBody
	public String stopTrack(@RequestParam(required = true) String trackingId) {
		return trackerService.stopTrack(trackingId).toString();
	}
}
