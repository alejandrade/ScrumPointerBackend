package com.gigamog.JavaWebSocketDemo.config;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.messaging.simp.stomp.StompReactorNettyCodec;
import org.springframework.messaging.tcp.reactor.ReactorNettyTcpClient;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

@Configuration
@EnableWebSocketMessageBroker
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {

    @Value("${com.gigamog.mq.username}")
    private String username;

    @Value("${com.gigamog.mq.password}")
    private String pass;

    @Value("${com.gigamog.mq.host}")
    private String host;

    @Value("${com.gigamog.mq.port}")
    private Integer port;

    @Override
    public void configureMessageBroker(MessageBrokerRegistry config) {

        config
                .setApplicationDestinationPrefixes("/app")
                .enableStompBrokerRelay("/topic")
                .setRelayHost(host) //
                .setRelayPort(port) //
                .setClientLogin(username) //
                .setClientPasscode(pass);

    }

    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        registry.addEndpoint("/websocket").setAllowedOrigins("*").withSockJS();
    }
}
