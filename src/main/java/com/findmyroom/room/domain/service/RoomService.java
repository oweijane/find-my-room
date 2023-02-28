package com.findmyroom.room.domain.service;

import com.findmyroom.room.domain.entity.Room;
import com.findmyroom.room.domain.repository.RoomRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoomService {
    private final boolean VACANT = true;
    private final boolean OCCUPIED = false;

    private final RoomRepository roomRepository;
    public RoomService(RoomRepository roomRepository) {
        this.roomRepository = roomRepository;
    }

    public Room getRoom(String roomId) {
        return roomRepository.getRoom(roomId);
    }

    public List<Room> getVacantRooms() {
        return roomRepository.getVacantRooms();
    }

    public void occupyRoom(String roomId) {
        roomRepository.updateRoom(roomId, OCCUPIED);
    }
    public void vacateRoom(String roomId) {
        roomRepository.updateRoom(roomId, VACANT);
    }
}
