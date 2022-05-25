package com.bookApp.service.impl;

import com.bookApp.dto.bean.RoomBean;
import com.bookApp.dto.bean.RoomEventBean;
import com.bookApp.dto.request.CreateRoomRequest;
import com.bookApp.dto.response.BookRoomResponse;
import com.bookApp.dto.response.CreateRoomResponse;
import com.bookApp.dto.response.GetAllRoomResponse;
import com.bookApp.dto.response.UpdaterRoomResponse;
import com.bookApp.model.RoomEvent;
import com.bookApp.repositoty.RoomRepository;
import com.bookApp.model.Room;
import com.bookApp.service.RoomEventService;
import com.bookApp.service.RoomService;
import com.bookApp.util.BaseResponse;
import com.bookApp.util.RoomEventMapper;
import com.bookApp.util.RoomMapper;
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
    public CreateRoomResponse createRoom(CreateRoomRequest roomBean) {

        Room room = RoomMapper.roomBeanToRoom(roomBean.getRoomBean());
        if(Objects.nonNull(room)) {
            Room save = repository.save(room);

            return new CreateRoomResponse(200, "Room was created", RoomMapper.roomToGetRoomBean(save));
        }
        return new CreateRoomResponse(400, "Room in request was null");
    }

    @Override
    public BaseResponse deleteRoom(Integer roomId) {
        Room room = repository.findById(roomId).orElse(null);
        if (Objects.nonNull(room)) {
            repository.delete(room);
            return new BaseResponse(200, "Room was deleted");
        }
        return new BaseResponse(400, "Room id is not correct");
    }
    @Transactional
    @Override
    public void deleteRoomsByIds(List<RoomBean> rooms) {
        List<Integer> ids = new ArrayList<>();
        for (RoomBean roomBean : rooms) {
            ids.add(roomBean.getId());
        }
        repository.deleteByIdIn(ids);
    }

    @Override
    public void updateRoomsByIds(List<RoomBean> roomBeans) {

    }

    @Override
    public BaseResponse updateRoom(RoomBean roomBean) {
        Room room = RoomMapper.roomBeanToRoom(roomBean);
        Room room1 = repository.findById(room.getId()).orElse(null);
        if (Objects.nonNull(room1)) {
            repository.save(room);
            return new UpdaterRoomResponse(200, "Room was updated", roomBean);
        }
        return new UpdaterRoomResponse(400, "Room id is not correct", roomBean);
    }

    @Override
    public GetAllRoomResponse getAllRoomList() {
        List<Room> roomList = repository.findAll();

        return new GetAllRoomResponse(200,"Rooms imported successfully", RoomMapper.roomListToRoomBeanList(roomList));
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
    public BookRoomResponse rentRoom(Integer userId, Integer roomId, RoomEvent roomEvent) {
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
                return new BookRoomResponse(200, "booking was confirmed", RoomMapper.roomToRoomBean(room1));
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
                        return new BookRoomResponse(200, "booking was confirmed", RoomMapper.roomToRoomBean(room1));
                    }
                }

            }
        }
        return new BookRoomResponse(400, "booking was canceled");
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
        return null;
    }

    @Override
    public List<RoomBean> getAllRomsByIds(List<Integer> ids) {
        List<Room> roomList = repository.findAllByIdIn(ids);
        return RoomMapper.roomListToRoomBeanList(roomList);
    }

    @Override
    public GetAllRoomResponse checkFreeRooms(Date startOfRent, Date endOfRent) {
        List<Integer> roomListIds = new ArrayList<>(roomEventService.checkFreeDates(startOfRent,endOfRent));

        for (Room room: repository.findAll() ) {
            if (room.getRoomEvents().isEmpty()) {
                roomListIds.add(room.getId());
            }
        }
        return new GetAllRoomResponse(200, "Rooms imported successfully",
                getAllRomsByIds(roomListIds));
    }
}
