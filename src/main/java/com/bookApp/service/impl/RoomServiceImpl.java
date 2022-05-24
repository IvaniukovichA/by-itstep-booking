package com.bookApp.service.impl;

import com.bookApp.model.RoomEvent;
import com.bookApp.repositoty.RoomRepository;
import com.bookApp.model.Room;
import com.bookApp.service.RoomEventService;
import com.bookApp.service.RoomService;
import com.bookApp.util.BaseResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.*;

@Service
public class RoomServiceImpl implements RoomService {

    @Autowired
    private RoomRepository repository;

    @Autowired
    private RoomEventService roomEventService;

    @Override
    public Room createRoom(Room room) {
        return repository.save(room);
    }

    @Override
    public BaseResponse deleteRoom(Integer roomId) {
        Room room = repository.findById(roomId).orElse(null);
        if (Objects.nonNull(room)) {
            repository.delete(room);
            return new BaseResponse(200, "Room was deleted", room);
        }
        return new BaseResponse(400, "Room id is not correct", roomId);
    }
    @Transactional
    @Override
    public void deleteRoomsByIds(List<Room> rooms) {
        List<Integer> ids = new ArrayList<>();
        for (Room room : rooms) {
            ids.add(room.getId());
        }
        repository.deleteByIdIn(ids);
    }

    @Override
    public BaseResponse updateRoom(Room room) {
        Room room1 = repository.findById(room.getId()).orElse(null);
        if (Objects.nonNull(room1)) {
            repository.save(room);
            return new BaseResponse(200, "Room was updated", room);
        }
        return new BaseResponse(400, "Room id is not correct", room);
    }

    @Override
    public List<Room> getAllRoomList() {
        return repository.findAll();
    }

    @Override
    public Room getRoomById(Integer roomId) {
        return repository.getById(roomId);
    }

    @Override
    public Room getRoomByUserId(Integer userId) {
        return repository.findRoomByUserId(userId);
    }

    @Override
    public BaseResponse rentRoom(Integer userId, Integer roomId, RoomEvent roomEvent) {
        if (Objects.nonNull(userId) && Objects.nonNull(roomId) && Objects.nonNull(roomEvent)) {
            roomEvent.setRoomId(roomId);
            Room room1 = repository.findById(roomId).orElse(null);
            List<RoomEvent> roomEventList = roomEventService.roomEventListById(roomId);
            if (roomEventList.isEmpty() && Objects.nonNull(room1)) {
                List<RoomEvent> roomEventList1 = new ArrayList<>();
                roomEventList1.add(roomEvent);
                room1.setRoomEvents(roomEventList1);
                if (Objects.isNull(room1.getQuantityOfCurrentRents())) {
                    room1.setQuantityOfCurrentRents(1);
                } else {
                    room1.setQuantityOfCurrentRents(room1.getQuantityOfCurrentRents() + 1);
                }
                room1.setUserId(userId);
                roomEventService.addRoomToEventTable(roomEvent);
                repository.save(room1);
                return new BaseResponse(200, "booking was confirmed", room1);
            } else {
                for (RoomEvent roomEvent2 : roomEventList) {
                    if (
                            (roomEvent2.getStartOfRent().after(roomEvent.getEndOfRent()) ||
                                    roomEvent2.getEndOfRent().before(roomEvent.getStartOfRent())) && Objects.nonNull(room1)
                    ) {
                        List<RoomEvent> roomEventList1 = room1.getRoomEvents();
                        roomEventList1.add(roomEvent);
                        room1.setRoomEvents(roomEventList1);
                        room1.setQuantityOfCurrentRents(room1.getQuantityOfCurrentRents() + 1);
                        room1.setUserId(userId);
                        roomEventService.addRoomToEventTable(roomEvent);
                        repository.save(room1);
                        return new BaseResponse(200, "booking was confirmed", room1);
                    }
                }

            }
        }
        return new BaseResponse(400, "booking was canceled", roomEvent);
    }

    @Override
    public BaseResponse unRantRoom(Integer roomId) {
//        Room roomToUnRent = repository.findById(roomId)
//                .orElse(null);
//
//        if(Objects.nonNull(roomToUnRent) && roomToUnRent.getIsClosedToRent()){
//            roomToUnRent.setUserId(null);
//            roomToUnRent.setIsClosedToRent(false);
//            Room savedRoom = repository.save(roomToUnRent);
//            return new BaseResponse(200, "Room is un rent", savedRoom);
//        }
        return new BaseResponse(400, "Room is not un rent", roomId);
    }

    @Override
    public List<Room> getAllRomsByIds(List<Integer> ids) {
        return repository.findAllByIdIn(ids);
    }

    @Override
    public List<Room> checkFreeRooms(Date startOfRent, Date endOfRent) {
        List<Integer> roomListIds = new ArrayList<>(roomEventService.checkFreeDates(startOfRent,endOfRent));

        for (Room room: repository.findAll() ) {
            if (room.getRoomEvents().isEmpty()) {
                roomListIds.add(room.getId());
            }
        }
        return getAllRomsByIds(roomListIds);
    }
}
