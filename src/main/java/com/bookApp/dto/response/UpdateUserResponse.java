package com.bookApp.dto.response;

import com.bookApp.dto.bean.UserBean;
import com.bookApp.util.BaseResponse;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdateUserResponse extends BaseResponse {
    private UserBean userBean;

    public UpdateUserResponse(Integer code, String message, UserBean userBean) {
        super(code, message);
        this.userBean = userBean;
    }
}
