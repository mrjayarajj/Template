package org.springframework.wst.questions;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

public class WSQuestionsHandler extends TextWebSocketHandler {

	private static final Logger LOG = LoggerFactory.getLogger(WSQuestionsHandler.class);

	private List<WebSocketSession> sessions = new CopyOnWriteArrayList<>();

	public void afterConnectionEstablished(WebSocketSession session) throws Exception {
		sessions.add(session);
	}

	protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
		for (WebSocketSession s : sessions) {
			try {
				s.sendMessage(new TextMessage(message.getPayload()));
			} catch (IOException e) {
				LOG.error(e.getMessage(), e);
			} catch (RuntimeException e) {
				//org.eclipse.jetty.websocket.api.WebSocketException
				LOG.error(e.getMessage(), e);
				sessions.remove(s);
			}
		}
	}

}
