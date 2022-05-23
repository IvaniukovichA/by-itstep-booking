package com.bookApp.service.impl;

import com.bookApp.repositoty.UserRepository;
import com.bookApp.model.Room;
import com.bookApp.model.User;
import com.bookApp.service.RoomService;
import com.bookApp.service.UserService;
import com.bookApp.util.BaseResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository repository;

    @Autowired
    private RoomService roomService;


    @Override
    public User createUser(User user) {
        return repository.save(user);
    }

    @Override
    public BaseResponse deleteUser(Integer userId) {
        User user = repository.findById(userId).orElse(null);
        if (Objects.nonNull(user)) {
            List<Room> rooms = user.getRooms();
            roomService.deleteRoomsByIds(rooms);
            repository.delete(user);
            return new BaseResponse(200, "User was deleted", user);
        } else
            return new BaseResponse(200, "User not found", userId);
    }

    @Override
    public User updateUser(User user) {
        User user1 = repository.findById(user.getId()).orElse(null);
        if (Objects.nonNull(user1)) {
            user1.setName(user.getName());
            user1.setLastName(user.getLastName());
            return repository.save(user1);
        }
        return null;
    }

    @Override
    public List<User> getAllUsers() {
        return repository.findAll();
    }

    @Override
    public User getUserById(Integer userId) {
        return repository.findById(userId)
                .orElse(null);
    }

    @Override
    public BaseResponse addRoomToUser(List<Integer> roomsId, Integer userId) {
        List<Room> allRomsByIds = roomService.getAllRomsByIds(roomsId);
        User userById = getUserById(userId);
        if (!allRomsByIds.isEmpty() && Objects.nonNull(userById)) {
            userById.setRooms(allRomsByIds);
            for (Room room : allRomsByIds) {
                room.setOwnerId(userId);
                roomService.updateRoom(room);
            }
            User save = repository.save(userById);
            return new BaseResponse(200, "Rooms was added", save);
        }
        return new BaseResponse(400, "Rooms or user is not found - " +
                "user:" + userById +
                "rooms:" + roomsId,
                null);
    }

    @Override
    public BaseResponse deleteRoomFromUser(Integer roomId, Integer userId) {
        Room room1 = roomService.getRoomById(roomId);
        User userById = getUserById(userId);
        if (Objects.nonNull(userById)) {
            if (userById.getRooms().contains(room1)) {
                List<Room> rooms1 = userById.getRooms();
                rooms1.remove(room1);
                userById.setRooms(rooms1);
                roomService.deleteRoom(roomId);
            }

            User save = repository.save(userById);
            return new BaseResponse(200, "Room was deleted", save);
        }
        return new BaseResponse(400, "Room or user is not found - " +
                "user:" +
                "rooms:" + roomId,
                null);
    }


}
