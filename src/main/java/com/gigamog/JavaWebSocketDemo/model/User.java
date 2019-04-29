package com.gigamog.JavaWebSocketDemo.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@EqualsAndHashCode
public class User {
    private String id;
    private String name;
    private Double vote;
}