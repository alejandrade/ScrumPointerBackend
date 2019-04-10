package com.gigamog.JavaWebSocketDemo.config;

import com.gigamog.JavaWebSocketDemo.model.MessageType;
import com.gigamog.JavaWebSocketDemo.model.Room;
import com.gigamog.JavaWebSocketDemo.model.WebSocketChatMessage;
import com.gigamog.JavaWebSocketDemo.service.MessageService;
import com.gigamog.JavaWebSocketDemo.service.RoomService;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.event.EventListener;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.messaging.SessionConnectedEvent;
import org.springframework.web.socket.messaging.SessionDisconnectEvent;

@AllArgsConstructor
@Component
public class WebSocketChatEventListener {

    private static final Logger logger = LoggerFactory.getLogger(WebSocketChatEventListener.class);

    private RoomService roomService;
    private MessageService messageService;

    @EventListener
    public void handleWebSocketConnectListener(SessionConnectedEvent event) {
        logger.info("Received a new web socket connection");
    }

    @EventListener
    public void handleWebSocketDisconnectListener(SessionDisconnectEvent event) {
        StompHeaderAccessor headerAccessor = StompHeaderAccessor.wrap(event.getMessage());
        String username = (String) headerAccessor.getSessionAttributes().get("userName");
        String roomId = (String) headerAccessor.getSessionAttributes().get("roomId");
        if(username != null) {
            Room room = roomService.removeUserFromRoom(roomId, username);
            WebSocketChatMessage chatMessage = new WebSocketChatMessage();
            chatMessage.setType(MessageType.LEAVE);
            chatMessage.setSender(username);
            chatMessage.setRoom(room);
            messageService.sendMessage(roomId, chatMessage);
        }
    }
}
