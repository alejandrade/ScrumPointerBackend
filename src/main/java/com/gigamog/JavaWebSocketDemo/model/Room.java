package com.gigamog.JavaWebSocketDemo.model;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@DynamoDBTable(tableName = "pointingPokerRoom")
public class Room {

    @DynamoDBHashKey
    private String id;

    private boolean showVotes;

    @DynamoDBAttribute(attributeName = "users")
    private List<User> users = new ArrayList<>();

    @DynamoDBAttribute(attributeName = "stories")
    private List<Story> stories = new ArrayList<>();

    @DynamoDBAttribute(attributeName = "currentStory")
    private Story currentStory = new Story();

    public Room(String id) {
        this.id = id;
    }
}
