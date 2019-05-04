package com.gigamog.JavaWebSocketDemo.config;

import com.gigamog.JavaWebSocketDemo.domain.MessageType;
import com.gigamog.JavaWebSocketDemo.domain.Room;
import com.gigamog.JavaWebSocketDemo.domain.User;
import com.gigamog.JavaWebSocketDemo.domain.WebSocketChatMessage;
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
        String userId = (String) headerAccessor.getSessionAttributes().get("userId");
        String roomId = (String) headerAccessor.getSessionAttributes().get("roomId");
        if(userId != null) {
            Room room = roomService.removeUserFromRoom(roomId, userId);
            WebSocketChatMessage chatMessage = new WebSocketChatMessage();
            chatMessage.setType(MessageType.UPDATE);
            chatMessage.setSender(new User(){{setId(userId);}});
            chatMessage.setRoom(room);
            messageService.sendMessage(roomId, chatMessage);
        }
    }
}
