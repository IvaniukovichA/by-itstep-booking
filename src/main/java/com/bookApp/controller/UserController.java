package com.bookApp.controller;

import com.bookApp.dto.bean.UserBean;
import com.bookApp.dto.request.CreateUserRequest;
import com.bookApp.dto.response.CreateUserResponse;
import com.bookApp.dto.response.DeleteUserResponse;
import com.bookApp.dto.response.GetAllUserResponse;
import com.bookApp.dto.response.UpdateUserResponse;
import com.bookApp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserController {

    @Qualifier("userServiceImpl")
    @Autowired
    private UserService service;

    @PostMapping("/createUser")
    public CreateUserResponse createUser(@RequestBody CreateUserRequest request) {
        return service.createUser(request);
    }

    @GetMapping("/getAllUser")
    public GetAllUserResponse getAllUser() {
        return service.getAllUsers();
    }

    @DeleteMapping("/user/{id}/delete")
    public DeleteUserResponse deleteUser(@PathVariable(name = "id") Integer id) {
        return service.deleteUser(id);
    }

    @PutMapping("/user/update")
    public UpdateUserResponse updateUser(@RequestBody UserBean userBean) {
        return service.updateUser(userBean);
    }

    @PutMapping("/user/{id}/update")
    public UpdateUserResponse updateUserRooms(@RequestBody List<Integer> roomIds, @PathVariable (name = "id") Integer id) {
        return service.addRoomToUser(roomIds, id);
    }
}
