package de.ishitasharma.timetracker.controller;

import java.time.LocalDateTime;
import java.util.Map;
import java.util.TreeMap;
import java.util.UUID;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import de.ishitasharma.timetracker.model.Tracker;

@Controller
@RequestMapping("/track")
public class TrackerController {

	private Map<String, Tracker> trackingInfo = new TreeMap<String, Tracker>();

	@RequestMapping(value = "/start", method = { RequestMethod.GET }, produces = { MediaType.APPLICATION_JSON_VALUE })
	@ResponseBody
	public String startTrack(
			@RequestParam(value = "message", defaultValue = "ok") String message) {
		Tracker tracker = new Tracker(message, LocalDateTime.now().toString(),
				UUID.randomUUID().toString());
		trackingInfo.put(tracker.getTrackingId(), tracker);
		return tracker.toString();
	}

	@RequestMapping(value = "/stop", method = { RequestMethod.GET }, produces = { MediaType.APPLICATION_JSON_VALUE })
	@ResponseBody
	public String stopTrack(@RequestParam(required = true) String trackingId) {
		
		return trackingInfo.get(trackingId).toString();
	}
}
