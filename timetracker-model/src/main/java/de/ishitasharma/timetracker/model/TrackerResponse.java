package de.ishitasharma.timetracker.model;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @author Ishita Sharma
 * Response which contains time tracking information
 */
public class TrackerResponse {

	@JsonProperty("Errors")
	private List<String> errors = new ArrayList<String>();
	
	@JsonProperty("StatusCode")
	private int statusCode;
	
	@JsonProperty("Message")
	private String message;

	@JsonProperty("Warnings")
	private String warning;
	
	@JsonProperty("trackers")
	private List<Tracker> trackers = new ArrayList<Tracker>();



	public List<String> getErrors() {
		return errors;
	}

	public void setErrors(List<String> errors) {
		this.errors = errors;
	}

	public void addErrors(String error) {
		this.errors.add(error);
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
