package com.bookApp.dto.bean;

import com.bookApp.model.Room;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


import java.util.List;

@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor
public class UserBean {

    private Integer id;
    private String name;
    private String lastName;
    private String role;
    private List<Room> rooms;

}
