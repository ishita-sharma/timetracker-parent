package de.ishitasharma.timetracker.controller;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.TreeSet;

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
	private Map<Integer, TreeSet<Object>> userInfo = new TreeMap<Integer, TreeSet<Object>>();
	TreeSet<Object> ts = new TreeSet<Object>();

	@RequestMapping(value = "/start", method = { RequestMethod.GET }, produces = { MediaType.APPLICATION_JSON_VALUE })
	@ResponseBody
	public String startTrack(
			@RequestParam(value = "message", defaultValue = "ok") String message,@RequestParam(value = "userName",required = true)String userName) {
		Tracker tracker = new Tracker(message,userName);
		ts.add(tracker);
		userInfo.put(userName.hashCode(), ts);
		trackingInfo.put(tracker.getmTrackingId(), tracker);
		return tracker.toString();
	}
	
	@RequestMapping(value = "/status", method = { RequestMethod.GET }, produces = { MediaType.APPLICATION_JSON_VALUE })
	@ResponseBody
	public String status(@RequestParam(required = true) String trackingId) {
		Tracker tracker = trackingInfo.get(trackingId);
		tracker.status();
		return tracker.toString();
	}
	
	@RequestMapping(value = "/user/history", method = {RequestMethod.GET}, produces = {MediaType.APPLICATION_JSON_VALUE})
	@ResponseBody
	public String userHistory(@RequestParam(required = true)String userName) {
		TreeSet ts = userInfo.get(userName.hashCode());
		List<String> result = new ArrayList<String>();
		for (Iterator<Tracker> iterator = ts.iterator(); iterator.hasNext();) {
			Tracker object = (Tracker) iterator.next();
			result.add(object.toString());
		}
		return result.toString();
	}
	
	@RequestMapping(value = "/stop", method = { RequestMethod.GET }, produces = { MediaType.APPLICATION_JSON_VALUE })
	@ResponseBody
	public String stopTrack(@RequestParam(required = true) String trackingId) {
		Tracker tracker = trackingInfo.get(trackingId);
		tracker.stop();
		return tracker.toString();
	}
}
