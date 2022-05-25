package com.bookApp.controller;


import com.bookApp.dto.bean.RoomEventBean;
import com.bookApp.dto.request.CreateRoomRequest;
import com.bookApp.dto.response.BookRoomResponse;
import com.bookApp.dto.response.CreateRoomResponse;
import com.bookApp.dto.response.GetAllRoomResponse;
import com.bookApp.model.RoomEvent;
import com.bookApp.service.RoomService;
import com.bookApp.util.RoomEventMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class RoomController {

    @Autowired
    private RoomService service;

    @PostMapping("/createRoom")
    public CreateRoomResponse createRoom(@RequestBody CreateRoomRequest request) {
        return service.createRoom(request);
    }

    @GetMapping("/getAllRooms")
    public GetAllRoomResponse getAllRooms() {
        return service.getAllRoomList();
    }

    @PutMapping("/room/event/{roomId}/{userId}")
    public BookRoomResponse bookRoom(
            @RequestBody RoomEventBean request,
            @PathVariable(name = "roomId") Integer roomId,
            @PathVariable(name = "userId") Integer userId) {
        return service.rentRoom(userId, roomId, RoomEventMapper.roomEventFromRoomEventBean(request));
    }

    @GetMapping("/getAllRoomsByDate")
    public GetAllRoomResponse checkFreeDates(@RequestBody RoomEventBean request) {
        return service.checkFreeRooms(
                request.getStartOfRent(),request.getEndOfRent()
        );
    }
}
