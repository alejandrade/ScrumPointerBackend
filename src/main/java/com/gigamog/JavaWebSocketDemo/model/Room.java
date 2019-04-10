package com.gigamog.JavaWebSocketDemo.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class Room {
    private String id;
    private boolean showVotes;
    private List<User> users;
    private List<Story> stories;
    private Story currentStory;

    public Room(String id) {
        this.id = id;
    }
}