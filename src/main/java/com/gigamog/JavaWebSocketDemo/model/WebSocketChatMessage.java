package com.gigamog.JavaWebSocketDemo.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class WebSocketChatMessage {
    private MessageType type;
    private User sender;
    private Room room;
}
