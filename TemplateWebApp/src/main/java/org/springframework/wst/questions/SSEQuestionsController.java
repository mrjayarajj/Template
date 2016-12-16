package org.springframework.wst.questions;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

@RestController
public class SSEQuestionsController {
	
	private static final Logger LOG = LoggerFactory.getLogger(SSEQuestionsController.class);

	private List<SseEmitter> emitters = new CopyOnWriteArrayList<SseEmitter>();

	@Autowired
	private HttpServletRequest req;

	@Autowired
	private HttpServletResponse res;

	@RequestMapping("/sse-questions")
	public SseEmitter questions() {

		SseEmitter sseEmitter = new SseEmitter(1000l * 60l * 60l);// millisec*sec*min=3600000
																	// millisec = 1 hour
		
		//add each client into QuestionsController singleton object
		emitters.add(sseEmitter);

		sseEmitter.onCompletion(() -> emitters.remove(sseEmitter));

		return sseEmitter;
	}

	@RequestMapping(value = "/sse-new-questions", method = RequestMethod.POST)
	public void postQuestions(@RequestBody String question) {
		System.out.println("question : "+question);
		for (SseEmitter sseEmitter : emitters) {
			try {
				sseEmitter.send(SseEmitter.event().name("spring").data(question));
			} catch (IOException e) {
				//Broken pipe
				//means new browser tab is opened and closed, while closing it was not removed from the list 
				//throw new RuntimeException(e);
				LOG.error(e.getMessage(),e);
			}
		}
	}
	
	
	
	

}
