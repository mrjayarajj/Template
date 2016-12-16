package org.springframework.wst.questions;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class STOMPostQuestionController {

	private SimpMessagingTemplate template;

	@Autowired
    public STOMPostQuestionController(SimpMessagingTemplate template) {
        this.template = template;
    }

	@RequestMapping("/stom-post-questions")
	public @ResponseBody String sayHello(@RequestParam("question") String question) {
		this.template.convertAndSend("/topic/stom-sub-questions", new Greeting("Welcome STOM, " + question + "!"));
		return "response sent to /topic/sjs-sub-questions also " + question;
	}

}
