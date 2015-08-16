package de.ishitasharma.timetracker.service.tests;

import javax.inject.Inject;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import de.ishitasharma.timetracker.storage.business.ITrackerStorage;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/test/resources/timetracker-service-context.xml")

public class TrackerServiceTest {
	@Inject
	@Qualifier("tracker-map-storage")
	private ITrackerStorage storage;
	
	@Before
	public void setup(){
		Assert.assertNotNull(storage);
		storage.createCustomer("testcustomer");
		storage.createUser("testuser", "testcustomer");
	}
	
	@After
	public void tearDown(){
		storage.clear();
	}
	
	@Test
	public void createValidCustomer(){
		Assert.assertTrue(storage.createCustomer("customer1").equals("success"));
	}
	
	@Test
	public void createCustomerInvalidData(){
		Assert.assertTrue(storage.createCustomer("").startsWith("invalid c"));
	}
	
	@Test
	public void createUserNonExistCust(){
		Assert.assertTrue(storage.createUser("user1", "testcustomer2").startsWith("customer d"));
	}
	
	@Test
	public void createUserInvalidData(){
		Assert.assertTrue(storage.createUser("", "testcustomer").startsWith("invalid u"));
	}
	
	@Test
	public void createUser(){
		Assert.assertTrue(storage.createUser("user1", "testcustomer").equals("success"));
	}
	
}
