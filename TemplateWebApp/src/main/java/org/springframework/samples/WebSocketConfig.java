package org.springframework.samples;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.AbstractWebSocketMessageBrokerConfigurer;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;

@Configuration
@EnableWebSocketMessageBroker
public class WebSocketConfig extends AbstractWebSocketMessageBrokerConfigurer {

	/**
	 * Enable STOM sub-protocol
	 */
	public void configureMessageBroker(MessageBrokerRegistry config) {
		config.enableSimpleBroker("/topic");
		config.setApplicationDestinationPrefixes("/app");
	}

	/**
	 * Enable SOCKJS sub-protocol into STOM
	 */
	public void registerStompEndpoints(StompEndpointRegistry registry) {
		registry.addEndpoint("/hello").withSockJS().setInterceptors(new HttpSessionIdHandshakeInterceptor());
		;
	}

}