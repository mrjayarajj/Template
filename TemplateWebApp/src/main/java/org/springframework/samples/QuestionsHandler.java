package org.springframework.samples;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

public class QuestionsHandler extends TextWebSocketHandler  {

	private List<WebSocketSession> sessions = new CopyOnWriteArrayList<>();
	
	public void afterConnectionEstablished(WebSocketSession session) throws Exception {
		sessions.add(session);
	}
	
	protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
		for(WebSocketSession s : sessions){
			s.sendMessage(message);
			sessions.remove(s);
		}
	}

}
