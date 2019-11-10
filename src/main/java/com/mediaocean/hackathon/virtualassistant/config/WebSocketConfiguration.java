package com.mediaocean.hackathon.virtualassistant.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

@Configuration
@EnableWebSocketMessageBroker
public class WebSocketConfiguration implements WebSocketMessageBrokerConfigurer {

    @Value("server.servlet.context-path")
    private String applicationContext;

    @Value("sockjs.fallback.endpoint")
    private String sockJSFallbackEndPoint;

    @Value("message.destination.prefix")
    private String messageDestinationPrefix;

    @Override
    public void configureMessageBroker(MessageBrokerRegistry registry) {
        //prefix to senTo annotation e.d /topic/eventHandler
        registry.enableSimpleBroker(messageDestinationPrefix);
        //prefix to message-mapping annotation e.g. /virtual-assistant/handleEvent
        registry.setApplicationDestinationPrefixes(applicationContext);
    }

    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        //enabling SockJS fallback options so that alternate transports may be used if WebSocket is not available
        registry.addEndpoint(sockJSFallbackEndPoint).withSockJS();
    }
}
