package com.bookApp.dto.response;

import com.bookApp.dto.bean.RoomBean;
import com.bookApp.util.BaseResponse;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class GetAllRoomResponse extends BaseResponse {
    private List<RoomBean> roomBeans;

    public GetAllRoomResponse(Integer code, String message) {
        super(code, message);
    }

    public GetAllRoomResponse(Integer code, String message, List<RoomBean> roomBeans) {
        super(code, message);
        this.roomBeans = roomBeans;
    }
}

