package com.bookApp.controller;

import com.bookApp.model.Room;

import com.bookApp.model.RoomEvent;
import com.bookApp.service.RoomService;
import com.bookApp.util.BaseResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class RoomController {

    @Autowired
    private RoomService service;

    @PostMapping("/createRoom")
    public Room createRoom(@RequestBody Room room) {
        return service.createRoom(room);
    }

    @GetMapping("/getAllRooms")
    public List<Room> getAllRooms() {
        return service.getAllRoomList();
    }

    @DeleteMapping("/room/delete/{id}")
    public BaseResponse deleteRoom(@PathVariable(name = "id") Integer id) {
        return service.deleteRoom(id);
    }

    @PutMapping("/room/update")
    public BaseResponse updateRoom(@RequestBody Room room) {
        return service.updateRoom(room);
    }

    @PutMapping("/room/event/{roomId}/{userId}/")
    public BaseResponse bookRoom(
            @RequestBody RoomEvent roomEvent,
            @PathVariable(name = "roomId") Integer roomId,
            @PathVariable(name = "userId") Integer userId) {
        return service.rentRoom(userId, roomId, roomEvent);
    }
    @GetMapping("/getAllRoomsByDate")
    public List<Room>checkFreeDates(@RequestBody RoomEvent roomEvent) {
        return service.checkFreeRooms(
                roomEvent.getStartOfRent(),roomEvent.getEndOfRent()
        );
    }
}
