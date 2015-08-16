package de.ishitasharma.timetracker.storage.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.inject.Named;
import javax.sql.DataSource;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Component;

import de.ishitasharma.timetracker.model.Tracker;
import de.ishitasharma.timetracker.storage.business.ITrackerStorage;

@Component
@Named("tracker-sqlite-storage")
public class SqliteStorageImpl implements ITrackerStorage, InitializingBean {

	private DataSource dataSource;
	private NamedParameterJdbcTemplate namedParameterJdbcTemplate;
	private Map<String, String> queries;
	private KeyHolder holder = new GeneratedKeyHolder();

	@Override
	public void afterPropertiesSet() throws Exception {

		this.namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(
				dataSource);
		queries = new HashMap<String, String>();
		/**
		 * Setting the queries
		 */
		queries.put("insertCustomer",
				"INSERT INTO tt_customer(name) "
				+ "VALUES(:CUSTOMER_NAME)");
		
		queries.put(
				"insertUser",
				"INSERT INTO tt_user(customer_id,user_name) "
				+ "SELECT customer_id,:USER_NAME "
				+ "FROM tt_customer "
				+ "WHERE name=:CUSTOMER_NAME");
		
		queries.put(
				"startTrack",
				"INSERT INTO tt_tracker(tracker_id,tracker_starttime,customer_id,user_id,tracker_status,tracker_message,tracker_elapsedtime) "
				+ "SELECT :TRACKER_ID,:TRACKER_STARTTIME,user.customer_id,user.user_id,:TRACKER_STATUS,:TRACKER_MESSAGE,:TRACKER_ELAPSEDTIME "
				+ "FROM tt_user user, tt_customer customer "
				+ "WHERE user.customer_id = customer.customer_id "
				+ "AND user.user_name = :USER_NAME "
				+ "AND customer.name = :CUSTOMER_NAME");
		
		queries.put("selectStatus", 
				"SELECT tracker.tracker_id, tracker.tracker_starttime, tracker.tracker_status, tracker.tracker_message, tracker.tracker_elapsedtime, user.user_name,customer.name"
				+ " FROM tt_tracker tracker, tt_user user, tt_customer customer " 
				+ "WHERE "
				+ "user.customer_id=customer.customer_id "
				+ "AND tracker.customer_id=customer.customer_id "
				+ "AND user.user_id=tracker.user_id "
				+ "AND tracker_id = :TRACKER_ID");
		
		queries.put("userHistory", "SELECT tracker.tracker_id, tracker.tracker_starttime, tracker.tracker_status, tracker.tracker_message, tracker.tracker_elapsedtime, user.user_name,customer.name"
				+ " FROM tt_tracker tracker, tt_user user, tt_customer customer " 
				+ "WHERE "
				+ "user.customer_id=customer.customer_id "
				+ "AND tracker.customer_id=customer.customer_id "
				+ "AND user.user_id=tracker.user_id "
				+ "AND user.user_name =:USER_NAME "
				+ "AND customer.name =:CUSTOMER_NAME");
		
		queries.put("stopTrack-1", "SELECT tracker.tracker_id, tracker.tracker_starttime, tracker.tracker_status, tracker.tracker_message, tracker.tracker_elapsedtime, user.user_name,customer.name"
				+ " FROM tt_tracker tracker, tt_user user, tt_customer customer " 
				+ "WHERE "
				+ "user.customer_id=customer.customer_id "
				+ "AND tracker.customer_id=customer.customer_id "
				+ "AND user.user_id=tracker.user_id "
				+ "AND tracker_id = :TRACKER_ID");
		
		queries.put("stopTrack-2", "UPDATE tt_tracker SET tracker_elapsedTime=:ELAPSED_TIME, tracker_status='stopped' WHERE tracker_id=:TRACKER_ID");
	}

	@Override
	public String createCustomer(String customerName) {
		MapSqlParameterSource paramMap = new MapSqlParameterSource();
		paramMap.addValue("CUSTOMER_NAME", customerName);
		namedParameterJdbcTemplate.update(queries.get("insertCustomer"),
				paramMap, holder);
		return "success";
	}

	@Override
	public String createUser(String userName, String customerName) {
		MapSqlParameterSource paramMap = new MapSqlParameterSource();
		paramMap.addValue("CUSTOMER_NAME", customerName);
		paramMap.addValue("USER_NAME", userName);
		namedParameterJdbcTemplate.update(queries.get("insertUser"), paramMap,
				holder);
		return "success";
	}

	@Override
	public Tracker stopTrack(String trackingId) {
		MapSqlParameterSource paramMap = new MapSqlParameterSource();
		paramMap.addValue("TRACKER_ID", trackingId);

		Map<String, Object> list =namedParameterJdbcTemplate.queryForMap(queries.get("stopTrack-1"), paramMap);
		Tracker tracker = new Tracker(list.get("tracker_id").toString(), Long.parseLong(list.get("tracker_starttime").toString()),list.get("user_name").toString(),list.get("tracker_status").toString(),list.get("tracker_message").toString(),Long.parseLong(list.get("tracker_elapsedTime").toString()));
		
		paramMap.addValue("ELAPSED_TIME", tracker.getmElapsedTime());

		namedParameterJdbcTemplate.update(queries.get("stopTrack-2"), paramMap);
		tracker.setmStatus("Stopped");
		return tracker;
	}

	@Override
	public List<Tracker> userHistory(String userName, String customerName) {
		MapSqlParameterSource paramMap = new MapSqlParameterSource();
		paramMap.addValue("USER_NAME", userName);
		paramMap.addValue("CUSTOMER_NAME", customerName);
		List<Map<String, Object>> list =namedParameterJdbcTemplate.queryForList(queries.get("userHistory"), paramMap);
		List<Tracker> result = new ArrayList<Tracker>();
		for (Iterator iterator = list.iterator(); iterator.hasNext();) {
			Map<String, Object> map = (Map<String, Object>) iterator.next();
			Tracker tracker = new Tracker(map.get("tracker_id").toString(), Long.parseLong(map.get("tracker_starttime").toString()),map.get("user_name").toString(),map.get("tracker_status").toString(),map.get("tracker_message").toString(),Long.parseLong(map.get("tracker_elapsedTime").toString()));
			result.add(tracker);
		}
		return result;
	}

	@Override
	public Tracker status(String trackingId) {
		MapSqlParameterSource paramMap = new MapSqlParameterSource();
		paramMap.addValue("TRACKER_ID", trackingId);
		Map<String, Object> list =namedParameterJdbcTemplate.queryForMap(queries.get("selectStatus"), paramMap);
		Tracker tracker = new Tracker(list.get("tracker_id").toString(), Long.parseLong(list.get("tracker_starttime").toString()),list.get("user_name").toString(),list.get("tracker_status").toString(),list.get("tracker_message").toString(),Long.parseLong(list.get("tracker_elapsedTime").toString()));
		return tracker;
	}

	@Override
	public Tracker startTrack(String message, String userName,
			String customerName) {
		Tracker tracker = new Tracker(message, userName);
		MapSqlParameterSource paramMap = new MapSqlParameterSource();
		paramMap.addValue("CUSTOMER_NAME", customerName);
		paramMap.addValue("USER_NAME", userName);
		paramMap.addValue("TRACKER_ID", tracker.getmTrackingId());
		paramMap.addValue("TRACKER_STARTTIME", tracker.getmStartTime());
		paramMap.addValue("TRACKER_STATUS", tracker.getmStatus());
		paramMap.addValue("TRACKER_MESSAGE", tracker.getmMessage());
		paramMap.addValue("TRACKER_ELAPSEDTIME", 0);

		if(namedParameterJdbcTemplate.update(queries.get("startTrack"), paramMap) ==0){
			
			tracker = null;
			}
		return tracker;
	}

	public DataSource getDataSource() {
		return dataSource;
	}

	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	@Override
	public void clear() {
		// TODO Auto-generated method stub
		
	}
}
