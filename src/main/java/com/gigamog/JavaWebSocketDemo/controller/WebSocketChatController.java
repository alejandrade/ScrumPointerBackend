package com.gigamog.JavaWebSocketDemo.controller;

import com.gigamog.JavaWebSocketDemo.model.MessageType;
import com.gigamog.JavaWebSocketDemo.model.Room;
import com.gigamog.JavaWebSocketDemo.model.WebSocketChatMessage;
import com.gigamog.JavaWebSocketDemo.service.MessageService;
import com.gigamog.JavaWebSocketDemo.service.RoomService;
import lombok.AllArgsConstructor;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.stereotype.Controller;

@AllArgsConstructor
@Controller
public class WebSocketChatController {

    private RoomService roomService;
    private MessageService messageService;

    @MessageMapping("/chat/{roomId}/sendMessage")
    public void sendMessage(@DestinationVariable String roomId, @Payload WebSocketChatMessage chatMessage, SimpMessageHeaderAccessor headerAccessor) {
        Room update = roomService.update(chatMessage.getRoom());
        chatMessage.setRoom(update);
        messageService.sendMessage(roomId, chatMessage);
    }

    @MessageMapping("/chat/{roomId}/addUser")
    public void addUser(@DestinationVariable String roomId, @Payload WebSocketChatMessage chatMessage, SimpMessageHeaderAccessor headerAccessor) {
        headerAccessor.getSessionAttributes().put("roomId", roomId);
        headerAccessor.getSessionAttributes().put("userId", chatMessage.getSender().getId());
        Room room = roomService.addUser(chatMessage.getRoom(), chatMessage.getSender());
        chatMessage.setRoom(room);
        chatMessage.setType(MessageType.UPDATE);
        messageService.sendMessage(roomId, chatMessage);
    }

    @MessageMapping("/chat/{roomId}/sync")
    public void syncRoom(@DestinationVariable String roomId, @Payload WebSocketChatMessage chatMessage) {
        Room room = roomService.get(roomId);
        chatMessage.setRoom(room);
        chatMessage.setType(MessageType.SYNC);
        messageService.sendMessage(roomId, chatMessage);
    }

}
;