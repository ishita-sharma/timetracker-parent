package de.ishitasharma.timetracker.storage.business;

import java.util.List;

import de.ishitasharma.timetracker.model.Tracker;

public interface ITrackerStorage {

	String createCustomer(String customerName);

	Tracker stopTrack(String trackingId);

	List<Tracker> userHistory(String userName, String customerName);

	Tracker status(String trackingId);

	Tracker startTrack(String message, String userName, String customerName);

	String createUser(String userName, String customerName);

	void clear();

}
