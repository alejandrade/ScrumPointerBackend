package com.gigamog.JavaWebSocketDemo.service;

import com.gigamog.JavaWebSocketDemo.model.WebSocketChatMessage;
import lombok.AllArgsConstructor;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.stereotype.Service;

import static java.lang.String.format;

@AllArgsConstructor
@Service
public class MessageService {
    private static final String QUEUE = "/topic/room/%s";

    private SimpMessageSendingOperations messagingTemplate;

    public void sendMessage(String roomId, WebSocketChatMessage message) {
        messagingTemplate.convertAndSend(format(QUEUE, roomId), message);
    }

}
