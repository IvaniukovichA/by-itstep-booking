package com.bookApp.dto.response;

import com.bookApp.dto.bean.UserBean;
import com.bookApp.util.BaseResponse;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class GetAllUserResponse extends BaseResponse {
    List<UserBean> userBeans;

    public GetAllUserResponse(Integer code, String message) {
        super(code, message);
    }

    public GetAllUserResponse(Integer code, String message, List<UserBean> userBeans) {
        super(code, message);
        this.userBeans = userBeans;
    }
}
