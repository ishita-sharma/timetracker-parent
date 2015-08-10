package de.ishitasharma.timetracker.service.tests;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestContext;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.context.WebApplicationContext;

import de.ishitasharma.timetracker.service.TrackerService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {TestContext.class, WebApplicationContext.class}, locations = "classpath:timetracker-service-context.xml")
@WebAppConfiguration
public class TrackerServiceTest {
	private MockMvc mockMvc;
	
	@Autowired
	private TrackerService service;
	
	@Test
	public void createValidCustomer() {
		
	}
	
	@Test
	public void createCustomerInvalidData() {
		
	}
}
