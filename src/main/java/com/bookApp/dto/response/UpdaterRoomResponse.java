package com.bookApp.dto.response;

import com.bookApp.dto.bean.RoomBean;
import com.bookApp.util.BaseResponse;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdaterRoomResponse extends BaseResponse {
    private RoomBean roomBean;

    public UpdaterRoomResponse(Integer code, String message) {
        super(code, message);
    }

    public UpdaterRoomResponse(Integer code, String message, RoomBean roomBean) {
        super(code, message);
        this.roomBean = roomBean;
    }
}
