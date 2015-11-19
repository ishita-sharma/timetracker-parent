package de.ishitasharma.timetracker.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import de.ishitasharma.timetracker.model.Tracker;
import de.ishitasharma.timetracker.storage.business.ITrackerStorage;

@Component
public class TrackerService implements ITrackerService{
	
	@Inject			//Ask how to use constructor then? Which constructor will spring use? Will a new object be created everytime 
					// this class's method is called
	@Qualifier("tracker-map-storage")
	private ITrackerStorage trackerStorage;
	
	@Override
	public String createCustomer(String customerName) {
		return trackerStorage.createCustomer(customerName);
	}

	@Override
	public String createUser(String userName, String customerName){
		return trackerStorage.createUser(userName, customerName);
	}
	
	@Override
	public Tracker startTrack(String message, String userName,
			String customerName) {
		return trackerStorage.startTrack(message, userName, customerName);
	}

	@Override
	public Tracker status(String trackingId) {
		return trackerStorage.status(trackingId);
	}

	@Override
	public List<Tracker> userHistory(String userName, String customerName) {
		return trackerStorage.userHistory(userName, customerName);
	}

	@Override
	public Tracker stopTrack(String trackingId) {
		return trackerStorage.stopTrack(trackingId);
	}
}
