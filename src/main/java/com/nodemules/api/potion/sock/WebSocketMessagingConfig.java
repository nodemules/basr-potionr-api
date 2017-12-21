package com.nodemules.api.potion.sock;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.AbstractWebSocketMessageBrokerConfigurer;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;

/**
 * @author brent
 * @since 12/20/17.
 */
@Configuration
@EnableWebSocketMessageBroker
public class WebSocketMessagingConfig extends AbstractWebSocketMessageBrokerConfigurer {

  @Override
  public void configureMessageBroker(MessageBrokerRegistry config) {
    config.enableSimpleBroker("/topic");
  }

  @Override
  public void registerStompEndpoints(StompEndpointRegistry stompEndpointRegistry) {
    stompEndpointRegistry.addEndpoint("/sock/potion").setAllowedOrigins("*").withSockJS();
  }
}
