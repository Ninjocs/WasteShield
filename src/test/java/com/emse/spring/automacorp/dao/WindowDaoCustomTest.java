package com.emse.spring.automacorp.dao;

import com.emse.spring.automacorp.model.WindowEntity;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@ExtendWith(SpringExtension.class)
class WindowDaoCustomTest {

    @Autowired
    private WindowDao windowDao;

    @Test
    public void shouldFindRoomsWithOpenWindows() {
        Long roomId = -8L;
        List<WindowEntity> openWindows = windowDao.findRoomsWithOpenWindows(roomId);

        assertThat(openWindows).isNotEmpty();
        assertThat(openWindows.get(0).getName()).isEqualTo("Window 1");
    }
}

