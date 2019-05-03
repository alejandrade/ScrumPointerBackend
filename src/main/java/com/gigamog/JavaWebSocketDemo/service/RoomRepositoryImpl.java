package com.gigamog.JavaWebSocketDemo.service;

import com.gigamog.JavaWebSocketDemo.model.Room;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;

/**
 * we are using a hashmap but if we wanted to make this production ready we would need to use a database
 */
@Repository
public class RoomRepositoryImpl implements RoomRepository {

    private Map<String, Room> rooms;

    public RoomRepositoryImpl(){
        rooms = new HashMap<>();
    }

    @Override
    public Room create(Room room) {
        rooms.put(room.getId(), room);
        return room;
    }

    @Override
    public void remove(Room room) {
        rooms.remove(room.getId());
    }

    @Override
    public Room get(Room room) {
        return rooms.get(room.getId());
    }

    @Override
    public Room update(Room room) {
        rooms.put(room.getId(), room);
        return rooms.get(room.getId());
    }
}
