package com.gigamog.JavaWebSocketDemo.domain;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@EqualsAndHashCode
public class User {
    private String id;
    private String name;
    private Double vote;
    private boolean spectator;
}