package com.gigamog.JavaWebSocketDemo.model;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBDocument;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@EqualsAndHashCode
@DynamoDBDocument
public class User {
    private String id;
    private String name;
    private Double vote;
}