package com.bookApp.service.impl;

import com.bookApp.model.RoomEvent;
import com.bookApp.repositoty.RoomEventRepository;
import com.bookApp.service.RoomEventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class RoomEventServiceImpl implements RoomEventService {

    @Autowired
    private RoomEventRepository repository;

    @Override
    public Set<Integer> checkFreeDates(Date startOfRent, Date endOfRent) {

        List<RoomEvent> roomEventList = repository.findAll();
        Set<Integer> freeRoomIds = new HashSet<>();
        for (RoomEvent roomEvent : roomEventList) {
            if (
                    startOfRent.after(roomEvent.getEndOfRent()) || endOfRent.before(roomEvent.getStartOfRent())
            ) {
                freeRoomIds.add(roomEvent.getRoomId());
            }
        }
        return freeRoomIds;
    }

    @Override
    public List<RoomEvent> roomEventListById(Integer roomId) {
        return repository.findAllByRoomId(roomId);
    }

    @Override
    public void addRoomToEventTable(RoomEvent roomEvent) {
        repository.save(roomEvent);
    }


}
