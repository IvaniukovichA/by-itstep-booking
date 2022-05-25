package com.bookApp.service.impl;

import com.bookApp.dto.bean.RoomBean;
import com.bookApp.dto.bean.UserBean;
import com.bookApp.dto.request.CreateUserRequest;
import com.bookApp.dto.response.CreateUserResponse;
import com.bookApp.dto.response.DeleteUserResponse;
import com.bookApp.dto.response.GetAllUserResponse;
import com.bookApp.dto.response.UpdateUserResponse;
import com.bookApp.repositoty.UserRepository;
import com.bookApp.model.Room;
import com.bookApp.model.User;
import com.bookApp.service.RoomService;
import com.bookApp.service.UserService;
import com.bookApp.util.BaseResponse;
import com.bookApp.util.RoomMapper;
import com.bookApp.util.UserMapper;
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
    public CreateUserResponse createUser(CreateUserRequest request) {
        User user;
        if(Objects.nonNull(request)) {
            user = UserMapper.userFromUserBean(request.getCreateUserBean());
            repository.save(user);
            return new CreateUserResponse(200, "User was created");
        }
        return new CreateUserResponse(200, "Request was wrong");
    }

    @Override
    public DeleteUserResponse deleteUser(Integer userId) {
        User user = repository.findById(userId).orElse(null);
        if (Objects.nonNull(user)) {
            List<Room> rooms = user.getRooms();
            repository.delete(user);
            roomService.deleteRoomsByIds(RoomMapper.roomListToRoomBeanList(rooms));
            return new DeleteUserResponse(200, "User was deleted");
        } else
            return new DeleteUserResponse(200, "User not found");
    }

    @Override
    public UpdateUserResponse updateUser(UserBean userBean) {
        User user = UserMapper.userFromUserBean(userBean);
        User user1 = repository.findById(user.getId()).orElse(null);
        if (Objects.nonNull(user1)) {
            user1.setName(user.getName());
            user1.setLastName(user.getLastName());
            repository.save(user1);
            return new UpdateUserResponse(200, "User was updating", userBean);
        }
        return new UpdateUserResponse(400, "User update error", userBean);
    }

    @Override
    public GetAllUserResponse getAllUsers() {
        List<User> users = repository.findAll();
        List<UserBean> userBeans= UserMapper.userBeanListFomUserList(users);
        return new GetAllUserResponse(
                200,
                "Users was exported from base successfully",
                userBeans
                );
    }

    @Override
    public User getUserById(Integer userId) {
        return repository.findById(userId)
                .orElse(null);
    }

    @Override
    public UpdateUserResponse addRoomToUser(List<Integer> roomsId, Integer userId) {
        List<RoomBean> allRomsByIds = roomService.getAllRomsByIds(roomsId);
        User userById = getUserById(userId);
        if (!allRomsByIds.isEmpty() && Objects.nonNull(userById)) {
            userById.setRooms(RoomMapper.roomBeanListToRoomList(allRomsByIds));
            for (RoomBean roomBean : allRomsByIds) {
                roomBean.setOwnerId(userId);
                roomService.updateRoom(roomBean);
            }
            UserBean save = UserMapper.userBeanFromUser(repository.save(userById));
            return new UpdateUserResponse(200, "Rooms was added", save);
        }
        return new UpdateUserResponse(400, "Rooms or user is not found - " +
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

            return new BaseResponse(200, "Room was deleted");
        }
        return new BaseResponse(400, "Room or user is not found - " +
                "user:" +
                "rooms:" + roomId);
    }


}
