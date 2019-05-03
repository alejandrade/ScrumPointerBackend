package com.gigamog.JavaWebSocketDemo.service;

import com.gigamog.JavaWebSocketDemo.model.Room;
import com.gigamog.JavaWebSocketDemo.model.User;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class RoomService {

    private RoomRepository roomRepository;

    public Room addUser(Room newRoom, User sender) {
        Room savedRoom = roomRepository.get(newRoom);
        if (savedRoom == null) {
            return roomRepository.create(newRoom);
        }

        savedRoom.getUsers().add(sender);
        return savedRoom;
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
