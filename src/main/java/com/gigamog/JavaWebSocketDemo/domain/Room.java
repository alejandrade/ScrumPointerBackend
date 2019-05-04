package com.gigamog.JavaWebSocketDemo.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Document(collection = "room")
public class Room {

    @Id
    private String id;
    private boolean showVotes;
    private List<User> users;
    private List<Story> stories;
    private Story currentStory;
    private LocalDateTime createdDate;
    public Room(String id) {
        this.id = id;
    }
}
