package com.bookApp.service;

import com.bookApp.dto.bean.UserBean;
import com.bookApp.dto.request.CreateUserRequest;
import com.bookApp.dto.response.CreateUserResponse;
import com.bookApp.dto.response.DeleteUserResponse;
import com.bookApp.dto.response.GetAllUserResponse;
import com.bookApp.dto.response.UpdateUserResponse;
import com.bookApp.model.User;
import com.bookApp.util.BaseResponse;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public interface UserService {

    CreateUserResponse createUser(CreateUserRequest request);
    DeleteUserResponse deleteUser(Integer userId);
    UpdateUserResponse updateUser(UserBean userBean);
    GetAllUserResponse getAllUsers();
    User getUserById(Integer userId);
    UpdateUserResponse addRoomToUser(List<Integer> roomsId, Integer userId);
    BaseResponse deleteRoomFromUser(Integer roomId, Integer userId);
}
