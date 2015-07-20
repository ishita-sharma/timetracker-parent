package de.ishitasharma.timetracker.model;

import java.util.UUID;

import org.joda.time.DateTime;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

//@JsonAutoDetect(fieldVisibility = Visibility.ANY, getterVisibility = Visibility.NONE, setterVisibility = Visibility.NONE)
public class Tracker {
	
	@JsonProperty("Message")
	private String mMessage;
	@JsonProperty("Start Time")
	private long mStartTime;
	@JsonProperty("TrackingId")
	private String mTrackingId;
	@JsonProperty("Elapsed Time")
	private long mElapsedTime;
	//TODO create enum
	@JsonProperty("Status")
	private String mStatus;
	
	
	public Tracker(){}
	
	public Tracker(String message) {
		mMessage = message;
		mStartTime = new DateTime().getMillis();
		mTrackingId = UUID.randomUUID().toString();
		mStatus = "Start";
	}
	public void status(){
		if(mStatus.equalsIgnoreCase("Start")){
			mStatus = "Started";
		}
	}
	public void stop(){
		if((mStatus.equalsIgnoreCase("Started"))||(mStatus.equalsIgnoreCase("Start"))){
			mElapsedTime = (new DateTime().getMillis())- mStartTime;
			mStatus = "Stop";
		}
		//TODO else error handling
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
	
	public void setmMessage(String message){
		mMessage = message;
	}
	
	public String getmMessage(){
		return mMessage;
	}
	
	public void setmStartTime(long currentTime){
		mStartTime = currentTime;
	}
	
	public long getmStartTime(){
		return mStartTime;
	}
	
	public void setmTrackingId(String trackerId){
		mTrackingId = trackerId;
	}
	
	public String getmTrackingId(){
		return mTrackingId;
	}
	
	public void setmElapsedTime(long elapsedTime){
		mElapsedTime = elapsedTime;
	}
	
	public long getmElapsedTime(){
		if(mElapsedTime == 0 && mStatus.equalsIgnoreCase("Started")){
			return (new DateTime().getMillis())- mStartTime;
		}
		return mElapsedTime;
	}

	public String getmStatus() {
		return mStatus;
	}

	public void setmStatus(String status) {
		mStatus = status;
	}
}
