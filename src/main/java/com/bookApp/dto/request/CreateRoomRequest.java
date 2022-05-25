package com.bookApp.dto.request;

import com.bookApp.dto.bean.GetRoomBean;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@NoArgsConstructor
public class CreateRoomRequest {
    private GetRoomBean roomBean;

    public CreateRoomRequest(GetRoomBean roomBean) {
        this.roomBean = roomBean;
    }
}
