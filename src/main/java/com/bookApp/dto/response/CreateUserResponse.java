package com.bookApp.dto.response;

import com.bookApp.dto.bean.CreateUserBean;
import com.bookApp.util.BaseResponse;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateUserResponse extends BaseResponse {
    private CreateUserBean userBean;

    public CreateUserResponse(Integer code, String message, CreateUserBean userBean) {
        super(code, message);
        this.userBean = userBean;
    }

    public CreateUserResponse(Integer code, String message) {
        super(code, message);
    }
}
