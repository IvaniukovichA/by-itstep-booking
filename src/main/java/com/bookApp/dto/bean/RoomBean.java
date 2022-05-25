package com.bookApp.dto.bean;

import com.bookApp.model.RoomEvent;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.math.BigDecimal;
import java.util.List;

@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor
public class RoomBean {

    private Integer id;
    private String address;
    private BigDecimal price;
    private Integer userId;
    private Integer ownerId;
    private Integer quantityOfCurrentRents;
    private List<RoomEvent> roomEvents;
}
