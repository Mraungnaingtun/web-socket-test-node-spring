package com.logant.chatting.Configuration;

import java.util.Collections;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.client.standard.StandardWebSocketClient;
import org.springframework.web.socket.messaging.WebSocketStompClient;
import org.springframework.web.socket.sockjs.client.SockJsClient;
import org.springframework.web.socket.sockjs.client.WebSocketTransport;

@Component
@Configuration
public class WebSocketConfig {

    @Bean
    public WebSocketStompClient stompClient() {
        StandardWebSocketClient standardWebSocketClient = new StandardWebSocketClient();
        return new WebSocketStompClient(
            new SockJsClient(Collections.singletonList(new WebSocketTransport(standardWebSocketClient)))
        );
    }
}