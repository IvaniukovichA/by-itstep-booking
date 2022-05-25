package com.bookApp.dto.response;

import com.bookApp.dto.bean.UserBean;
import com.bookApp.util.BaseResponse;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DeleteUserResponse extends BaseResponse {
    private UserBean userBean;

    public DeleteUserResponse(Integer code, String message) {
        super(code, message);
    }

    public DeleteUserResponse(Integer code, String message, UserBean userBean) {
        super(code, message);
        this.userBean = userBean;
    }
}
