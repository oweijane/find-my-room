package com.findmyroom.room.domain.entity;

import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbAttribute;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbBean;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbPartitionKey;

@DynamoDbBean
public class Room {

    private String roomId;
    private boolean roomStatus;

    @DynamoDbPartitionKey
    @DynamoDbAttribute("roomId")
    public String getRoomId() {
        return roomId;
    }
    public void setRoomId(String roomId) {
        this.roomId = roomId;
    }

    public boolean getRoomStatus() {
        return roomStatus;
    }
    public void setRoomStatus(boolean roomStatus) {
        this.roomStatus = roomStatus;
    }
}
