package com.gigamog.JavaWebSocketDemo.service;

import com.gigamog.JavaWebSocketDemo.model.Room;
import com.gigamog.JavaWebSocketDemo.model.User;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

@Service
@AllArgsConstructor
public class RoomService {

    private RoomRepository roomRepository;

    public Room addUser(String roomId, User sender) {
        Room room = roomRepository.get(new Room(roomId));
        if (room == null) {
            room = roomRepository.create(new Room(roomId));
            room.setCreatedDate(LocalDateTime.now());
        }
        room.getUsers().add(sender);
        return room;
    }

    public Room removeUserFromRoom(String currentRoomId, String userId) {
        Room room = roomRepository.get(new Room(currentRoomId));
        if (room != null) {
            List<User> users = room.getUsers();
            users.remove(users.stream().filter(x->x.getId().equals(userId)).findAny().orElse(null));
            if (users.isEmpty()) {
                roomRepository.remove(room);
            }
        }
        return room;
    }

    public Room update(Room room){
        return roomRepository.update(room);
    }

    public Room get(String roomId) {
        return roomRepository.get(new Room(roomId));
    }
}
