package org.springframework.wst.questions;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

@Controller
public class STOMQuestionController {

	@MessageMapping("/stom-questions")
	@SendTo("/topic/stom-sub-questions")
	public Greeting greeting(HelloMessage message) throws Exception {
		return new Greeting(message.getName().toUpperCase());
	}

}