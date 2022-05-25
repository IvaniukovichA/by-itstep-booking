package com.bookApp.util;

import com.bookApp.dto.bean.RoomEventBean;
import com.bookApp.model.RoomEvent;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class RoomEventMapper {
    public static RoomEventBean roomEventBeanFromRoomEvent(RoomEvent roomEvent) {
        RoomEventBean roomEventBean = new RoomEventBean();
        if (Objects.nonNull(roomEvent)) {
            roomEventBean.setEventId(roomEvent.getEventId());
            roomEventBean.setRoomId(roomEvent.getRoomId());
            roomEventBean.setStartOfRent(roomEvent.getStartOfRent());
            roomEventBean.setEndOfRent(roomEvent.getEndOfRent());
            return roomEventBean;
        }
        return null;
    }

    public static List<RoomEventBean> roomEventBeanListFromRoomEventList(List<RoomEvent> roomEvents) {
        List<RoomEventBean> roomEventBeans = new ArrayList<>();
        if (!roomEvents.isEmpty()) {
            for (RoomEvent roomEvent : roomEvents) {
                roomEventBeans.add(roomEventBeanFromRoomEvent(roomEvent));
            }
        }
        return roomEventBeans;
    }

    public static RoomEvent roomEventFromRoomEventBean(RoomEventBean roomEventBean) {
        RoomEvent roomEvent = new RoomEvent();
        if (Objects.nonNull(roomEventBean)) {
            roomEvent.setEventId(roomEventBean.getEventId());
            roomEvent.setRoomId(roomEventBean.getRoomId());
            roomEvent.setStartOfRent(roomEventBean.getStartOfRent());
            roomEvent.setEndOfRent(roomEventBean.getEndOfRent());
            return roomEvent;
        }
        return null;
    }

    public static List<RoomEvent> roomEventListFromRoomEventBeanList(List<RoomEventBean> roomEventBeans) {
        List<RoomEvent> roomEvents = new ArrayList<>();
        if (!roomEventBeans.isEmpty()) {
            for (RoomEventBean roomEventBean : roomEventBeans) {
                roomEvents.add(roomEventFromRoomEventBean(roomEventBean));
            }
        }
        return roomEvents;
    }


}
