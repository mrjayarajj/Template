package org.springframework.wst.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;
import org.springframework.wst.questions.EchoHandler;
import org.springframework.wst.questions.SJSQuestionsHandler;
import org.springframework.wst.questions.WSQuestionsHandler;

@Configuration
@EnableWebSocket
public class SimpleWebSocketConfig implements WebSocketConfigurer {

	public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
		registry.addHandler(new EchoHandler(), "/echo");
		registry.addHandler(new WSQuestionsHandler(), "/ws-questions");
		registry.addHandler(new SJSQuestionsHandler(), "/sjs-questions").withSockJS();
	}


}