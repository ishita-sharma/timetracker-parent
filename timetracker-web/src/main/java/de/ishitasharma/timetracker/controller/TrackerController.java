package de.ishitasharma.timetracker.controller;


import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import de.ishitasharma.timetracker.model.Customer;
import de.ishitasharma.timetracker.model.TrackerResponse;
import de.ishitasharma.timetracker.service.ITrackerService;

@Controller
@RequestMapping("/track")
public class TrackerController extends AController {

	@Inject
	private ITrackerService trackerService;  //how many times a new object of this class be created? 
											 //every time a request is sent?

	@RequestMapping(value = "/create/customer", method = { RequestMethod.GET }, produces = { MediaType.APPLICATION_JSON_VALUE })
	@ResponseBody
	public String createCustomer(
			@RequestParam(value = "customerName", required = true) Customer customerName, 
			@RequestParam(value = "callback", required = false) String callback){
		return jsonResponse(trackerService.createCustomer(customerName.getCustomername()),callback);
	}
	
	@RequestMapping(value = "/get/customer", method = { RequestMethod.POST }, produces = { MediaType.APPLICATION_JSON_VALUE })
	@ResponseBody
	public String getCustomer(@Validated @RequestBody Customer customer) {
		return jsonResponse(trackerService.createCustomer(customer.getCustomername()),"");
	}
	
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	@ResponseBody
	public TrackerResponse catchEx(MethodArgumentNotValidException ex, HttpServletRequest req){
		TrackerResponse trackerResponse = new TrackerResponse();
		trackerResponse.addErrors(ex.getBindingResult().getAllErrors().get(0).getDefaultMessage());
		return trackerResponse;
	}
	
	
	@RequestMapping(value = "/create/user", method = { RequestMethod.GET }, produces = { MediaType.APPLICATION_JSON_VALUE })
	@ResponseBody
	public String createUser(
			@RequestParam(value = "customerName", required = true) String customerName,
			@RequestParam(value = "userName", required = true) String userName,
			@RequestParam(value = "callback", required = false) String callback) {
		return jsonResponse(trackerService.createUser(userName,customerName),callback);
	}

	@RequestMapping(value = "/start", method = { RequestMethod.GET }, produces = { MediaType.APPLICATION_JSON_VALUE })
	@ResponseBody
	public String startTrack(
			@RequestParam(value = "message", defaultValue = "ok") String message,
			@RequestParam(value = "userName", required = true) String userName,
			@RequestParam(value = "customerName", required = true) String customerName,
			@RequestParam(value = "callback", required = false) String callback) {
		return jsonResponse(trackerService.startTrack(message, userName, customerName),callback);
	}

	@RequestMapping(value = "/status", method = { RequestMethod.GET }, produces = { MediaType.APPLICATION_JSON_VALUE })
	@ResponseBody
	public String status(
			@RequestParam(required = true) String trackingId,
   		 	@RequestParam(value = "callback", required = false) String callback) {
		return jsonResponse(trackerService.status(trackingId),callback);
	}

	@RequestMapping(value = "/user/history", method = { RequestMethod.GET }, produces = { MediaType.APPLICATION_JSON_VALUE })
	@ResponseBody
	public String userHistory(@RequestParam(required = true) String userName,
			@RequestParam(required = true) String customerName,
			@RequestParam(value = "callback", required = false) String callback) {
		return jsonResponse(trackerService.userHistory(userName, customerName),callback);
	}

	@RequestMapping(value = "/stop", method = { RequestMethod.GET }, produces = { MediaType.APPLICATION_JSON_VALUE })
	@ResponseBody
	public String stopTrack(@RequestParam(required = true) String trackingId,
			@RequestParam(value = "callback", required = false) String callback) {
		return jsonResponse(trackerService.stopTrack(trackingId),callback);
	}
}
