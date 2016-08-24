package org.springframework.samples;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HelloController {

	private SimpMessagingTemplate template;

	@Autowired
    public HelloController(SimpMessagingTemplate template) {
        this.template = template;
    }

	@RequestMapping("/welcome")
	public @ResponseBody String sayHello(@RequestParam("name") String name) {
		this.template.convertAndSend("/topic/greetings", new Greeting("Hello, " + name + "!"));
		return "response sent to /topic/greeting also " + name;
	}

}
