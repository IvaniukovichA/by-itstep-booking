package com.bookApp.service;

import com.bookApp.model.User;
import com.bookApp.util.BaseResponse;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public interface UserService {

    User createUser(User user);
    BaseResponse deleteUser(Integer userId);
    User updateUser(User user);
    List<User> getAllUsers();
    User getUserById(Integer userId);
    BaseResponse addRoomToUser(List<Integer> roomsId, Integer userId);
    BaseResponse deleteRoomFromUser(Integer roomId, Integer userId);
}
