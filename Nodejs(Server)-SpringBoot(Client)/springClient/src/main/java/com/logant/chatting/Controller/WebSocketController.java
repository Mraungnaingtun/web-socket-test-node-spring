package com.logant.chatting.Controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.logant.chatting.Service.WebSocketClientService;

@RestController
public class WebSocketController {

    private final WebSocketClientService webSocketClientService;

    public WebSocketController(WebSocketClientService webSocketClientService) {
        this.webSocketClientService = webSocketClientService;
    }

    @GetMapping("/send-message")
    public String sendMessage(@RequestParam String message) {
        webSocketClientService.connectAndSendMessage(message);
        return "This is Client message " + message;
    }
}