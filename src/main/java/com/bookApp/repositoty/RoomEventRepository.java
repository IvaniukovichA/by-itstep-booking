package com.bookApp.repositoty;

import com.bookApp.model.RoomEvent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface RoomEventRepository extends JpaRepository<RoomEvent, Integer> {

    List<RoomEvent> findAllByRoomId(Integer roomId);
}
