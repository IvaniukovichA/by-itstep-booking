package com.bookApp.dto.request;

import com.bookApp.dto.bean.CreateUserBean;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CreateUserRequest {
    private CreateUserBean createUserBean;
}
