package de.ishitasharma.timetracker.service;

import java.util.List;

import de.ishitasharma.timetracker.model.Tracker;

public interface ITrackerService {

	String createCustomer(String customerName);

	Tracker startTrack(String message, String userName, String customerName);

	Tracker status(String trackingId);

	List<Tracker> userHistory(String userName, String customerName);

	Tracker stopTrack(String trackingId);

}
