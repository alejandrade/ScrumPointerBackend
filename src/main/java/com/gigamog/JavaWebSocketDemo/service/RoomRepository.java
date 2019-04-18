package com.gigamog.JavaWebSocketDemo.service;

import com.gigamog.JavaWebSocketDemo.model.Room;

public interface RoomRepository {

    Room create(Room room);
    void remove(Room room);
    Room get(Room room);
    Room update(Room room);

}
