package de.ishitasharma.timetracker.model;

import java.util.UUID;

import org.joda.time.DateTime;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class Tracker implements Comparable<Object>{
	
	@JsonProperty("Message")
	private String mMessage;
	@JsonProperty("UserName")
	private String mUserName;
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
	
	public Tracker(String message,String userName) {
		mMessage = message;
		mUserName = userName;
		mStartTime = new DateTime().getMillis();
		mTrackingId = UUID.randomUUID().toString();
		mStatus = "Start";
	}
	
	public Tracker(String mTrackingId, long mStartTime, String mUserName, String mStatus, String mMessage, long mElapsedTime){
		super();
		this.mMessage = mMessage;
		this.mUserName = mUserName;
		this.mStartTime = mStartTime;
		this.mTrackingId = mTrackingId;
		this.mElapsedTime = mElapsedTime;
		this.mStatus = mStatus;
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
		if(mElapsedTime == 0){ 
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

	public String getmUserName() {
		return mUserName;
	}

	public void setmUserName(String userName) {
		mUserName = userName;
	}

	@Override
	public int compareTo(Object o) {
		// TODO Auto-generated method stub
		return 1;
	}
}
