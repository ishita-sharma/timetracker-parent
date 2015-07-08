package de.ishitasharma.timetracker.controller;

import java.io.IOException;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import de.ishitasharma.timetracker.model.Health;

@Controller
@RequestMapping("/health")
public class HealthController {

	@RequestMapping(value = "/check", method = { RequestMethod.GET }, produces = { MediaType.APPLICATION_JSON_VALUE })
	@ResponseBody
	public String healthCheck(
			@RequestParam(value = "message", defaultValue = "Ishita") String message,
			@RequestParam(value = "status", defaultValue = "error") String status)
			throws IOException {
		return new Health(message, status).toString();
	}
}
