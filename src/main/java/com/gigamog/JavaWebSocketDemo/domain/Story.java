package com.gigamog.JavaWebSocketDemo.domain;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Story {
    private Double score;
    private String storyName;
    private String id;
    private boolean consensus;
}
