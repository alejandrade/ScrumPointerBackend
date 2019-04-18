package com.gigamog.JavaWebSocketDemo.model;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBDocument;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@DynamoDBDocument
public class Story {
    private Double score;
    private String storyName;
    private String id;
}
