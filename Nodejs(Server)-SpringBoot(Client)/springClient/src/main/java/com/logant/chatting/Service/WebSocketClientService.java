package com.logant.chatting.Service;

import java.io.IOException;

import org.springframework.stereotype.Service;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.client.WebSocketConnectionManager;
import org.springframework.web.socket.client.standard.StandardWebSocketClient;
import org.springframework.web.socket.handler.AbstractWebSocketHandler;

@Service
public class WebSocketClientService {

    private final String wsUrl = "ws://localhost:8080"; // URL of the Node.js WebSocket server

    public void connectAndSendMessage(String message) {
        StandardWebSocketClient client = new StandardWebSocketClient();

        // Create the WebSocket handler
        AbstractWebSocketHandler handler = new AbstractWebSocketHandler() {
            @Override
            public void handleTextMessage(WebSocketSession session, TextMessage message) throws IOException {
                System.out.println("Received: " + message.getPayload());
            }

            @Override
            public void afterConnectionEstablished(WebSocketSession session) throws Exception {
                // Send a message after the connection is established
                session.sendMessage(new TextMessage(message));
            }
        };

        // Create the WebSocket connection manager
        WebSocketConnectionManager connectionManager = new WebSocketConnectionManager(
            client,
            handler,
            wsUrl
        );

        connectionManager.start();
    }
}
