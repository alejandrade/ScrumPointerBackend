package com.gigamog.JavaWebSocketDemo.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class Room {

    private String id;
    private boolean showVotes;
    private List<User> users = new ArrayList<>();
    private List<Story> stories = new ArrayList<>();
    private Story currentStory = new Story();
    private LocalDateTime createdDate;
    public Room(String id) {
        this.id = id;
    }
}
