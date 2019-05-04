package com.gigamog.JavaWebSocketDemo.service;

import com.gigamog.JavaWebSocketDemo.domain.Room;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

@Profile("mongo")
@AllArgsConstructor
@Repository
public class RoomMongoRepositoryImpl implements RoomRepository {

    private final RoomMongoAdapter roomMongoAdapter;

    @Override
    public Room create(Room room) {
        return roomMongoAdapter.save(room);
    }

    @Override
    public void remove(Room room) {
        roomMongoAdapter.deleteById(room.getId());
    }

    @Override
    public Room get(Room room) {
        return roomMongoAdapter.findById(room.getId()).orElse(null);
    }

    @Override
    public Room update(Room room) {
        return create(room);
    }
}
