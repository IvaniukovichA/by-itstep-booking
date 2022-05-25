package com.bookApp.model;


import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;

import javax.persistence.*;
import java.util.Date;

@ToString
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Table(name = "room_event")
public class RoomEvent {


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "event_id")
    private Integer eventId;

    @Column(name = "room_id")
    private Integer roomId;

    @Column(name = "start_of_rent")
    private Date startOfRent;

    @Column(name = "end_of_rent")
    private Date endOfRent;
}
