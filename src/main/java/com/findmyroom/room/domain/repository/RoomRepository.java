package com.findmyroom.room.domain.repository;

import com.findmyroom.room.domain.entity.Room;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbEnhancedClient;
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbTable;
import software.amazon.awssdk.enhanced.dynamodb.Key;
import software.amazon.awssdk.enhanced.dynamodb.TableSchema;

@Repository
public class RoomRepository {
    @Autowired
    private DynamoDbEnhancedClient dynamoDbenhancedClient;

    public void updateRoom(final String roomId, final boolean status) {
        DynamoDbTable<Room> roomTable = getTable();
        Room room = new Room();
        room.setRoomId(roomId);
        room.setStatus(status);
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
}
