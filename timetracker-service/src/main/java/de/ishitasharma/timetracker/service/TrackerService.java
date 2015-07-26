package de.ishitasharma.timetracker.service;

import javax.inject.Inject;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import de.ishitasharma.timetracker.storage.business.ITrackerStorage;

@Component
public class TrackerService implements ITrackerService{
	
	@Inject
	@Qualifier("tracker-map-storage")
	private ITrackerStorage trackerStorage;
	
	@Override
	public String createCustomer(String customerName) {
		return trackerStorage.createCustomer(customerName);
	}

	@Override
	public String startTrack(String message, String userName,
			String customerName) {
		return trackerStorage.startTrack(message, userName, customerName);
	}

	@Override
	public String status(String trackingId) {
		return trackerStorage.status(trackingId);
	}

	@Override
	public String userHistory(String userName, String customerName) {
		return trackerStorage.userHistory(userName, customerName);
	}

	@Override
	public String stopTrack(String trackingId) {
		return trackerStorage.stopTrack(trackingId);
	}
}
