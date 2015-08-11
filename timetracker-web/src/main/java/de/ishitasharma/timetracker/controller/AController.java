package de.ishitasharma.timetracker.controller;

import java.util.List;

import javax.validation.TraversableResolver;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import de.ishitasharma.timetracker.model.Tracker;
import de.ishitasharma.timetracker.model.TrackerResponse;

public abstract class AController {

	protected String jsonResponse(String message,String callback){
		
		ObjectMapper mapper = new ObjectMapper();
		TrackerResponse trackerResponse = new TrackerResponse();
		try{
			trackerResponse.setStatusCode(200);
			return mapper.writeValueAsString(trackerResponse);
			
		}catch (JsonProcessingException e){
			return "Cannot parse object into Json";
		}
	}
	
	protected String jsonResponse(Tracker tracker, String callback){
		
		ObjectMapper mapper = new ObjectMapper();
		TrackerResponse trackerResponse = new TrackerResponse();
		try{
			if(tracker == null){
				trackerResponse.setErrors("Timer could not be started");
				trackerResponse.setStatusCode(400);
			}else{
				trackerResponse.addTrackers(tracker);
				trackerResponse.setStatusCode(200);
			}
			
			if(callback==null)
				return mapper.writeValueAsString(trackerResponse);
			else
				return callback +"("+ mapper.writeValueAsString(trackerResponse) +");";
		}catch (JsonProcessingException e) {
			return "Cannot parse object into Json";
		}
	}
	
	protected String jsonResponse(List<Tracker> trackers, String callback){
		ObjectMapper mapper = new ObjectMapper();
		TrackerResponse trackerResponse = new TrackerResponse();
		trackerResponse.setStatusCode(200);
		try {
			trackerResponse.setTrackers(trackers);
			return mapper.writeValueAsString(trackerResponse);
		} catch (JsonProcessingException e) {
			return "Cannot parse object into Json";
		}
	}
}
