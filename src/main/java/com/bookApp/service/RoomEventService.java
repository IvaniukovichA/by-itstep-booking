package com.bookApp.service;

import com.bookApp.model.RoomEvent;

import java.util.Date;

import java.util.List;
import java.util.Set;

public interface RoomEventService {

    Set<Integer> checkFreeDates(Date startOfRent, Date endOfRent);
    List<RoomEvent> roomEventListById(Integer roomId);
    void addRoomToEventTable(RoomEvent roomEvent);
}
