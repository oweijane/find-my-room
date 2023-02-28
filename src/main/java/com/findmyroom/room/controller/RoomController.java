package com.findmyroom.room.controller;

import com.findmyroom.room.domain.entity.Room;
import com.findmyroom.room.domain.service.RoomService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Controller
public class RoomController {
    private final RoomService roomService;

    public RoomController(RoomService roomService) {
        this.roomService = roomService;
    }

    @GetMapping("/status")
    @ResponseBody
    public Room roomStatus(@RequestParam(value = "roomId") String roomId) {
        return roomService.getRoom(roomId);
    }

    @GetMapping("/occupy")
    public String occupyRoom(@RequestParam(value = "roomId") String roomId) {
        roomService.occupyRoom(roomId);
        return "occupy";
    }

    @GetMapping("/vacate")
    public String vacateRoom(@RequestParam(value = "roomId") String roomId) {
        roomService.vacateRoom(roomId);
        return "vacate";
    }

    @GetMapping("/vacant")
    @ResponseBody
    public List<Room> getVacantRooms() {
        return roomService.getVacantRooms();
    }
}
