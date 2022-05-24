package com.bookApp.controller;

import com.bookApp.model.User;
import com.bookApp.service.UserService;
import com.bookApp.util.BaseResponse;
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
    public User createUser(@RequestBody User user) {
        return service.createUser(user);
    }

    @GetMapping("/getAllUser")
    public List<User> getAllUser() {
        return service.getAllUsers();
    }

    @DeleteMapping("/user/{id}/delete")
    public BaseResponse deleteUser(@PathVariable(name = "id") Integer id) {
        return service.deleteUser(id);
    }

    @PutMapping("/user/update")
    public User updateUser(@RequestBody User user) {
        return service.updateUser(user);
    }

    @PutMapping("/user/{id}/update")
    public BaseResponse updateUserRooms(@RequestBody List<Integer> roomIds, @PathVariable (name = "id") Integer id) {
        return service.addRoomToUser(roomIds, id);
    }
}
