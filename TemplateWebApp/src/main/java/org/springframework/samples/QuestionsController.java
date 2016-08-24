package org.springframework.samples;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

@RestController
public class QuestionsController {

	private List<SseEmitter> emitters = new CopyOnWriteArrayList<>();

	@Autowired
	private HttpServletRequest req;

	@Autowired
	private HttpServletResponse res;

	@RequestMapping("/sse-questions")
	public SseEmitter questions() {

		SseEmitter sseEmitter = new SseEmitter(1000l * 60l * 60l);// millisec*sec*min=3600000
																	// millisec
		emitters.add(sseEmitter);

		sseEmitter.onCompletion(() -> emitters.remove(sseEmitter));

		return sseEmitter;
	}

	@RequestMapping(value = "/sse-new-questions", method = RequestMethod.POST)
	public void postQuestions(String question) {
		for (SseEmitter sseEmitter : emitters) {
			try {
				sseEmitter.send(SseEmitter.event().name("spring").data(question));
			} catch (IOException e) {
				throw new RuntimeException(e);
			}
		}
	}
	
	
	
	

}
