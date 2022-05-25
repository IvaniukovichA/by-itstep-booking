package com.bookApp.dto.bean;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor
public class CreateUserBean {
    private String name;
    private String lastName;
    private String role;
}
