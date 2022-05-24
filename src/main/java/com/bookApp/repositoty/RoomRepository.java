package com.bookApp.repositoty;

import com.bookApp.model.Room;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RoomRepository extends JpaRepository<Room, Integer> {

    //select * from room where id = userId;
    Room findRoomByUserId(Integer userId);

    //select * from room where IsClosedToRent = false;
//    List<Room> findAllByIsClosedToRentFalse();

    //Select * from room where id in (1,2,3,4,5,6,7)
    List<Room> findAllByIdIn(List<Integer> roomsId);

    void deleteByIdIn(List<Integer> ids);


}
