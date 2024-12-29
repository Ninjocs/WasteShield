package com.emse.spring.automacorp.dao;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@DataJpaTest
@ExtendWith(SpringExtension.class)
class SensorDaoTest {

    @Autowired
    private SensorDao sensorDao;

    @Test
    public void shouldFindSensorById() {
        SensorEntity sensor = sensorDao.getReferenceById(-10L);
        assertThat(sensor.getName()).isEqualTo("Temperature room 2");
        assertThat(sensor.getValue()).isEqualTo(21.3);
    }
}
