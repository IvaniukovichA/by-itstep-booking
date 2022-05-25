package com.bookApp.util;

import com.bookApp.dto.bean.GetRoomBean;
import com.bookApp.dto.bean.RoomBean;
import com.bookApp.model.Room;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class RoomMapper {
    public static Room roomBeanToRoom(RoomBean roomBean) {
        Room room = null;
        if (Objects.nonNull(roomBean)) {
            room = new Room();
            room.setId(roomBean.getId());
            room.setAddress(roomBean.getAddress());
            room.setPrice(roomBean.getPrice());
            room.setUserId(roomBean.getUserId());
            room.setOwnerId(roomBean.getOwnerId());
            room.setRoomEvents(roomBean.getRoomEvents());
            room.setQuantityOfCurrentRents(roomBean.getQuantityOfCurrentRents());
        }
        return room;
    }

    public static Room roomBeanToRoom(GetRoomBean roomBean) {
        Room room = null;
        if (Objects.nonNull(roomBean)) {
            room = new Room();
            room.setAddress(roomBean.getAddress());
            room.setPrice(roomBean.getPrice());
            room.setRoomEvents(roomBean.getRoomEvents());
            room.setQuantityOfCurrentRents(roomBean.getQuantityOfCurrentRents());
        }
        return room;
    }


    public static GetRoomBean roomToGetRoomBean(Room room) {
        GetRoomBean roomBean = null;
        if (Objects.nonNull(room)) {
            roomBean = new GetRoomBean();
            roomBean.setAddress(room.getAddress());
            roomBean.setPrice(room.getPrice());
            roomBean.setRoomEvents(room.getRoomEvents());
            roomBean.setQuantityOfCurrentRents(room.getQuantityOfCurrentRents());
        }
        return roomBean;
    }

    public static RoomBean roomToRoomBean(Room room) {
        RoomBean roomBean = null;
        if (Objects.nonNull(room)) {
            roomBean = new RoomBean();
            roomBean.setId(room.getId());
            roomBean.setAddress(room.getAddress());
            roomBean.setPrice(room.getPrice());
            roomBean.setRoomEvents(room.getRoomEvents());
            roomBean.setQuantityOfCurrentRents(room.getQuantityOfCurrentRents());
            roomBean.setUserId(room.getUserId());
            roomBean.setOwnerId(room.getOwnerId());
        }
        return roomBean;
    }


    public static List<RoomBean> roomListToRoomBeanList(List<Room> rooms) {
        List<RoomBean> roomBeans = new ArrayList<>();
        if (!rooms.isEmpty()) {
            for (Room room : rooms) {
                roomBeans.add(roomToRoomBean(room));
            }
        }
        return roomBeans;
    }

    public static List<Room> roomBeanListToRoomList(List<RoomBean> roomBeans) {
        List<Room> rooms = new ArrayList<>();
        if (!roomBeans.isEmpty()) {
            for (RoomBean roomBean : roomBeans) {
                rooms.add(roomBeanToRoom(roomBean));
            }
        }
        return rooms;
    }
}
