package com.gigamog.JavaWebSocketDemo.service;

import com.gigamog.JavaWebSocketDemo.domain.Room;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface RoomMongoAdapter extends MongoRepository<Room, String> {

    <S extends Room> S save(S entity);

    Optional<Room> findById(String s);

    void deleteById(String s);

}
