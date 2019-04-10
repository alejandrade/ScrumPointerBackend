package com.gigamog.JavaWebSocketDemo.controller;

import com.gigamog.JavaWebSocketDemo.model.MessageType;
import com.gigamog.JavaWebSocketDemo.model.Room;
import com.gigamog.JavaWebSocketDemo.model.WebSocketChatMessage;
import com.gigamog.JavaWebSocketDemo.service.MessageService;
import com.gigamog.JavaWebSocketDemo.service.RoomRepository;
import com.gigamog.JavaWebSocketDemo.service.RoomService;
import lombok.AllArgsConstructor;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.stereotype.Controller;

@AllArgsConstructor
@Controller
public class WebSocketChatController {

    private SimpMessageSendingOperations messagingTemplate;
    private RoomRepository roomRepository;
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
        headerAccessor.getSessionAttributes().put("userName", chatMessage.getSender());
        Room room = roomService.addUser(roomId, chatMessage.getSender());
        chatMessage.setRoom(room);
        chatMessage.setType(MessageType.UPDATE);
        messageService.sendMessage(roomId, chatMessage);
    }



    @MessageMapping("/chat/{roomId}/leaveuser")
    public void leaveRoom(@DestinationVariable String roomId, @Payload WebSocketChatMessage chatMessage,SimpMessageHeaderAccessor headerAccessor)
    {
        String currentRoomId = (String) headerAccessor.getSessionAttributes().put("roomId", roomId);
        String userName = (String) headerAccessor.getSessionAttributes().put("userName", chatMessage.getSender());

        if (currentRoomId != null) {
            WebSocketChatMessage leaveMessage = new WebSocketChatMessage();
            leaveMessage.setType(MessageType.LEAVE);
            leaveMessage.setSender(chatMessage.getSender());
            Room room = roomService.removeUserFromRoom(currentRoomId, userName);
            chatMessage.setRoom(room);
            messageService.sendMessage(roomId, chatMessage);
        }
    }


}
;