package com.bookApp.service;

import com.bookApp.dto.bean.RoomBean;
import com.bookApp.dto.bean.RoomEventBean;
import com.bookApp.dto.request.CreateRoomRequest;
import com.bookApp.dto.response.BookRoomResponse;
import com.bookApp.dto.response.CreateRoomResponse;
import com.bookApp.dto.response.GetAllRoomResponse;
import com.bookApp.model.Room;
import com.bookApp.model.RoomEvent;
import com.bookApp.util.BaseResponse;

import java.util.Date;
import java.util.List;

public interface RoomService {

    CreateRoomResponse createRoom(CreateRoomRequest roomBean);
    BaseResponse deleteRoom(Integer roomId);
    void deleteRoomsByIds(List<RoomBean> roomBeans);
    void updateRoomsByIds(List<RoomBean> roomBeans);
    BaseResponse updateRoom(RoomBean roomBean);

    GetAllRoomResponse getAllRoomList();
    Room getRoomById(Integer roomId);
    Room getRoomByUserId(Integer userId);
    BookRoomResponse rentRoom(Integer userId, Integer roomId, RoomEvent roomEvent);
    BaseResponse unRantRoom(Integer roomId);
    List<RoomBean> getAllRomsByIds(List<Integer> ids);
    GetAllRoomResponse checkFreeRooms(Date startOfRent, Date endOfRent);
}
