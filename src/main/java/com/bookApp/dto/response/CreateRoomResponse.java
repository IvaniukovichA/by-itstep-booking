package com.bookApp.dto.response;

import com.bookApp.dto.bean.GetRoomBean;
import com.bookApp.util.BaseResponse;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class CreateRoomResponse extends BaseResponse {
    private GetRoomBean roomBean;

    public CreateRoomResponse(Integer code, String message) {
        super(code, message);
    }

    public CreateRoomResponse(Integer code, String message, GetRoomBean roomBean) {
        super(code, message);
        this.roomBean = roomBean;
    }
}
