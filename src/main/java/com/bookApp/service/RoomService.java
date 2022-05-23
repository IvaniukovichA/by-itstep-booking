package com.bookApp.service;

import com.bookApp.model.Room;
import com.bookApp.model.RoomEvent;
import com.bookApp.util.BaseResponse;

import java.util.Date;
import java.util.List;

public interface RoomService {

    Room createRoom(Room room);
    BaseResponse deleteRoom(Integer roomId);
    void deleteRoomsByIds(List<Room> rooms);
    BaseResponse updateRoom(Room room);

    List<Room> getAllRoomList();
    Room getRoomById(Integer roomId);
    Room getRoomByUserId(Integer userId);
    BaseResponse rentRoom(Integer userId, Integer roomId, RoomEvent roomEvent);
    BaseResponse unRantRoom(Integer roomId);
    List<Room> getAllRomsByIds(List<Integer> ids);
    List<Room> checkFreeRooms(Date startOfRent, Date endOfRent);
}
