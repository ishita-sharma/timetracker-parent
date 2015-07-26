package de.ishitasharma.timetracker.service;

public interface ITrackerService {

	String createCustomer(String customerName);

	String startTrack(String message, String userName, String customerName);

	String status(String trackingId);

	String userHistory(String userName, String customerName);

	String stopTrack(String trackingId);

}
