package de.ishitasharma.timetracker.storage.impl;

import javax.inject.Named;

import org.springframework.stereotype.Component;
import de.ishitasharma.timetracker.storage.business.ITrackerStorage;
import de.ishitasharma.timetracker.model.Tracker;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.TreeSet;

@Component
@Named("tracker-map-storage")
public class MapStorageImpl implements ITrackerStorage {

	private Map<String, Tracker> trackingInfo = new TreeMap<String, Tracker>();
	private Map<Integer, TreeMap<Integer, TreeSet<Object>>> customerInfo = new TreeMap<Integer, TreeMap<Integer, TreeSet<Object>>>();
	// private TreeMap<Integer, TreeSet<Object>> userInfo = new TreeMap<Integer,
	// TreeSet<Object>>();
	TreeSet<Object> ts = new TreeSet<Object>();

	
	@Override
	public String createCustomer(String customerName) {
		customerInfo.put(customerName.hashCode(),
				new TreeMap<Integer, TreeSet<Object>>());
		return "{\"status\":\"success\"}";
	}

	@Override
	public String startTrack(String message, String userName,
			String customerName) {
		Tracker tracker = new Tracker(message, userName);

//		ts.add(tracker);
		customerInfo.get(customerName.hashCode()).put(userName.hashCode(),
				ts);
		customerInfo.get(customerName.hashCode()).get(userName.hashCode())
				.add(tracker);
		// ts.add(tracker);
		// customerInfo.put(customerName.hashCode(), userInfo);
		trackingInfo.put(tracker.getmTrackingId(), tracker);
		return tracker.toString();
	}

	@Override
	public String status(String trackingId) {
		Tracker tracker = trackingInfo.get(trackingId);
		tracker.status();
		return tracker.toString();
	}

	@Override
	public String userHistory(String userName, String customerName) {
		TreeMap<Integer, TreeSet<Object>> tm = (TreeMap<Integer, TreeSet<Object>>) customerInfo
				.get(customerName.hashCode());
		TreeSet ts = tm.get(userName.hashCode());
		List<String> result = new ArrayList<String>();
		for (Iterator<Tracker> iterator = ts.iterator(); iterator.hasNext();) {
			Tracker object = (Tracker) iterator.next();
			result.add(object.toString());
		}
		return result.toString();
	}

	@Override
	public String stopTrack(String trackingId) {
		Tracker tracker = trackingInfo.get(trackingId);
		tracker.stop();
		return tracker.toString();
	}
}
