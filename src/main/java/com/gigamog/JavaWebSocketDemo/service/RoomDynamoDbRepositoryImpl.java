package com.gigamog.JavaWebSocketDemo.service;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.gigamog.JavaWebSocketDemo.model.Room;
import lombok.AllArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@AllArgsConstructor
@Repository
public class RoomDynamoDbRepositoryImpl implements RoomRepository {


    private final DynamoDBMapper dynamoDBMapper;

    @Override
    public Room create(Room room) {
        String newId = UUID.randomUUID().toString();
        room.setId(StringUtils.defaultIfEmpty(room.getId(), newId));
        dynamoDBMapper.save(room);
        return room;
    }

    @Override
    public void remove(Room room) {
        dynamoDBMapper.delete(room);
    }

    @Override
    public Room get(Room room) {
        return dynamoDBMapper.load(room);
    }

    @Override
    public Room update(Room room) {
        dynamoDBMapper.save(room);
        return room;
    }
}
