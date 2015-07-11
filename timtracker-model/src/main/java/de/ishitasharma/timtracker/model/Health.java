package de.ishitasharma.timtracker.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;


public class Health {

//	jackson pojo json response rest
	
	@JsonProperty("STATUS")
	private String status;
	@JsonProperty("MESSAGE")
	private String message;
	
	public Health(){
		
	}
	
	public Health(String message, String status){
		this.message=message;
		this.status=status;
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
		this.status = status;
	}
	
	public String getStatus(){
		return status;
	}
	
	public void setMessage(String msg){
		message = msg;
	}
	
	public String getMessage(){
		return message;
	}
}
