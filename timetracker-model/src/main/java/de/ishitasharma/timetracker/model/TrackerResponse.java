package de.ishitasharma.timetracker.model;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class TrackerResponse {

	@JsonProperty("Error")
	private String errors;
	
	@JsonProperty("StatusCode")
	private int statusCode;
	
	@JsonProperty("Message")
	private String message;

	@JsonProperty("Warnings")
	private String warning;
	
	@JsonProperty("trackers")
	private List<Tracker> trackers = new ArrayList<Tracker>();

	public String getErrors() {
		return errors;
	}

	public void setErrors(String errors) {
		this.errors = errors;
	}

	public int getStatusCode() {
		return statusCode;
	}

	public void setStatusCode(int statusCode) {
		this.statusCode = statusCode;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getWarning() {
		return warning;
	}

	public void setWarning(String warning) {
		this.warning = warning;
	}

	public List<Tracker> getTrackers() {
		return trackers;
	}

	public void setTrackers(List<Tracker> trackers) {
		this.trackers = trackers;
	}

	public void addTrackers(Tracker tracker) {
		this.trackers.add(tracker);
	}
	
}
