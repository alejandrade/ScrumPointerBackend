package com.gigamog.JavaWebSocketDemo.service;

import com.gigamog.JavaWebSocketDemo.model.Room;
import com.gigamog.JavaWebSocketDemo.model.Story;
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
        room.getUsers().add(User.builder()
                .name(sender)
                .build());
        room.setCurrentStory(new Story());
        return room;
    }

    public void removeUserFromRoom(String currentRoomId, String userName) {
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
    }

    public Room update(Room room){
        return roomRepository.update(room);
    }
}
