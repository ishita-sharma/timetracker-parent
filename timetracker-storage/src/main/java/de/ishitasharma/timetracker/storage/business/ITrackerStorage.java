package de.ishitasharma.timetracker.storage.business;

public interface ITrackerStorage {

	String createCustomer(String customerName);

	String stopTrack(String trackingId);

	String userHistory(String userName, String customerName);

	String status(String trackingId);

	String startTrack(String message, String userName, String customerName);

}
