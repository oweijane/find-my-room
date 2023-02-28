package com.findmyroom.room.domain.repository;

import com.findmyroom.room.domain.entity.Room;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import software.amazon.awssdk.enhanced.dynamodb.*;
import software.amazon.awssdk.enhanced.dynamodb.model.ScanEnhancedRequest;
import software.amazon.awssdk.services.dynamodb.model.AttributeValue;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class RoomRepository {
    @Autowired
    private DynamoDbEnhancedClient dynamoDbenhancedClient;

    public void updateRoom(final String roomId, final boolean roomStatus) {
        DynamoDbTable<Room> roomTable = getTable();
        Room room = new Room();
        room.setRoomId(roomId);
        room.setRoomStatus(roomStatus);
        roomTable.updateItem(room);
    }


    public Room getRoom(final String roomId) {
        DynamoDbTable<Room> roomTable = getTable();
        Key key = Key.builder().partitionValue(roomId).build();
        return roomTable.getItem(key);
    }

    private DynamoDbTable<Room> getTable() {
        return dynamoDbenhancedClient.table("Room", TableSchema.fromBean(Room.class));
    }

    public List<Room> getVacantRooms() {
        DynamoDbTable<Room> roomTable = getTable();
        AttributeValue att = AttributeValue.builder()
                .bool(true)
                .build();

        Map<String, AttributeValue> expressionValues = new HashMap<>();
        expressionValues.put(":value", att);

        Expression expression = Expression.builder()
                .expression("roomStatus = :value")
                .expressionValues(expressionValues)
                .build();

        ScanEnhancedRequest scanEnhancedRequest = ScanEnhancedRequest.builder()
                .filterExpression(expression)
                .build();

        return roomTable.scan(scanEnhancedRequest).items().stream().toList();
    }
}
