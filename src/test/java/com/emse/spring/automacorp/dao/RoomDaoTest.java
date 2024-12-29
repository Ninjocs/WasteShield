package com.emse.spring.automacorp.dao;

import com.emse.spring.automacorp.model.RoomEntity;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import static org.assertj.core.api.Assertions.*;

@DataJpaTest
@ExtendWith(SpringExtension.class)
class RoomDaoTest {

    @Autowired
    private RoomDao roomDao;

    @Test
    public void shouldFindRoomById() {
        RoomEntity room = roomDao.getReferenceById(-10L);
        assertThat(room.getName()).isEqualTo("Room1");
        assertThat(room.getFloor()).isEqualTo(1);
    }
}

