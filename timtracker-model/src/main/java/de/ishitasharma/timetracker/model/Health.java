package de.ishitasharma.timetracker.model;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@JsonAutoDetect(fieldVisibility = Visibility.ANY, getterVisibility = Visibility.NONE, setterVisibility = Visibility.NONE)
public class Health {

//	jackson pojo json response rest
	
	@JsonProperty("STATUS")
	private String mStatus;
	@JsonProperty("MESSAGE")
	private String mMessage;
	
	public Health(){}
	
	public Health(String message, String status){
		mMessage=message;
		mStatus=status;
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
	
	public void setStatus(String status){
		mStatus = status;
	}
	
	public String getStatus(){
		return mStatus;
	}
	
	public void setMessage(String msg){
		mMessage = msg;
	}
	
	public String getMessage(){
		return mMessage;
	}
}
