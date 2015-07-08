package de.ishitasharma.timetracker.controller;

import java.io.IOException;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/health")
public class HealthController {

	@RequestMapping(value="/check",
			method={RequestMethod.GET},
	produces={MediaType.APPLICATION_JSON_VALUE})
	@ResponseBody
	public String healthCheck(@RequestParam(value="name",defaultValue="Ishita")String name) throws IOException{
//		PrintWriter ps = response.getWriter();
//		ps.write("ok");
//		ps.close();
		return "{\"status\":\"ok\",\"message\":\"Welcome "+name+"\"}";
	}
}
