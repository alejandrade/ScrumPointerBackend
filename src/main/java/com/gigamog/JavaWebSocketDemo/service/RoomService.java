package com.gigamog.JavaWebSocketDemo.service;

import com.gigamog.JavaWebSocketDemo.model.Room;
import com.gigamog.JavaWebSocketDemo.model.User;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
@AllArgsConstructor
public class RoomService {

    private RoomRepository roomRepository;

    public Room addUser(String roomId, String sender) {
        Room room = roomRepository.get(new Room(roomId));
        if (room == null) {
            room = roomRepository.create(new Room(roomId));
        }
        User user = new User();
        user.setName(sender);

        room.getUsers().add(user);
        return room;
    }

    public Room removeUserFromRoom(String currentRoomId, String userName) {
        Room room = roomRepository.get(new Room(currentRoomId));
        if (room != null) {
            List<User> users = room.getUsers();
            User user = null;
            for (User userInList : users) {
                if (Objects.equals(userInList.getName(), userName)) {
                    user = userInList;
                    break;
                }
            }

            if (user != null ){
                users.remove(user);
            }
            if (users.isEmpty()) {
                roomRepository.remove(room);
            }
        }
        return room;
    }

    public Room update(Room room){
        return roomRepository.update(room);
    }
}
