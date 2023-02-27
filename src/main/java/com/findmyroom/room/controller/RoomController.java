package com.findmyroom.room.controller;

import com.findmyroom.room.domain.entity.Room;
import com.findmyroom.room.domain.service.RoomService;
import org.springframework.web.bind.annotation.*;


@RestController
public class RoomController {
    private final RoomService roomService;

    public RoomController(RoomService roomService) {
        this.roomService = roomService;
    }

    @GetMapping("/status")
    public Room roomStatus(@RequestParam(value = "roomId") String roomId) {
        return roomService.getRoom(roomId);
    }

    @PatchMapping("/occupy")
    public void occupyRoom(@RequestParam(value = "roomId") String roomId) {
        roomService.occupyRoom(roomId);
    }

    @PatchMapping("/vacate")
    public void vacateRoom(@RequestParam(value = "roomId") String roomId) {
        roomService.vacateRoom(roomId);
    }
}
