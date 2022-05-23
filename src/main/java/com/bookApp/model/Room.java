package com.bookApp.model;


import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;

@ToString
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Table(name = "room")

public class Room {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "Id")
    private Integer id;
    @Column(name = "address")
    private String address;
    @Column(name = "price")
    private BigDecimal price;
    @Column(name = "user_id")
    private Integer userId;
    @Column(name = "owner_id")
    private Integer ownerId;
    @Column(name = "quantity_of_current_rents", columnDefinition = "integer default 25")
    private Integer quantityOfCurrentRents;
    @OneToMany
    private List<RoomEvent> roomEvents;

}
