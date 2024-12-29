package com.emse.spring.automacorp.dao;

import com.emse.spring.automacorp.model.WindowEntity;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import static org.assertj.core.api.Assertions.*;

@DataJpaTest // (1)
@ExtendWith(SpringExtension.class)
class WindowDaoTest {

    @Autowired // (2)
    private WindowDao windowDao;

    @Test
    public void shouldFindAWindowById() {
        WindowEntity window = windowDao.getReferenceById(-10L); // (3)
        assertThat(window.getName()).isEqualTo("Window 1");
        assertThat(window.getWindowStatus().getValue()).isEqualTo(1.0);
    }
}

