package de.ishitasharma.timetracker.model;

import java.time.LocalDateTime;
import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@JsonAutoDetect(fieldVisibility = Visibility.ANY, getterVisibility = Visibility.NONE, setterVisibility = Visibility.NONE)
public class Tracker {
	
	@JsonProperty("Message")
	private String mMessage;
	@JsonProperty("Starting Time")
	private String mCurrentTime;
	@JsonProperty("TrackingId")
	private String mTrackingId;
	
	
	public Tracker(){}
	
	public Tracker(String mTrackingId) {
		super();
		this.mTrackingId = mTrackingId;
	}
	
	public Tracker(String mMessage, String mCurrentTime, String mTrackerId) {
		super();
		this.mMessage = mMessage;
		this.mCurrentTime = mCurrentTime;
		this.mTrackingId = mTrackerId;
	}

	@Override
	public String toString(){
		ObjectMapper mapper = new ObjectMapper();
		try {
			return mapper.writeValueAsString(this);
		} catch (JsonProcessingException e) {
			return "Cannot parse object into Json";
		}
	}
	
	//getter and setter
	
	public void setMessage(String message){
		mMessage = message;
	}
	
	public String getMessage(){
		return mMessage;
	}
	
	public void setCurrentTime(String currentTime){
		mCurrentTime = currentTime;
	}
	
	public String getCurrentTime(){
		return mCurrentTime;
	}
	
	public void setTrackingId(String trackerId){
		mTrackingId = trackerId;
	}
	
	public String getTrackingId(){
		return mTrackingId;
	}
}
