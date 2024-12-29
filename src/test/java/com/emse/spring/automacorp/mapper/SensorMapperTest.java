package com.emse.spring.automacorp.mapper;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class SensorMapperTest {

    @Test
    public void testToSensor_withValidSensorEntity() {
        SensorEntity sensorEntity = new SensorEntity();
        sensorEntity.setId(1L);
        sensorEntity.setName("Temperature Sensor");
        sensorEntity.setValue(25.0);
        sensorEntity.setSensorType(SensorType.TEMPERATURE);

        Sensor sensorDTO = SensorMapper.toSensor(sensorEntity);

        assertNotNull(sensorDTO);
        assertEquals(1L, sensorDTO.id());
        assertEquals("Temperature Sensor", sensorDTO.name());
        assertEquals(25.0, sensorDTO.value());
        assertEquals("TEMPERATURE", sensorDTO.sensorType());
    }

    @Test
    public void testToSensor_withNullSensorEntity() {
        Sensor sensorDTO = SensorMapper.toSensor(null);

        assertNull(sensorDTO);
    }

    @Test
    public void testToSensorEntity_withValidSensorDTO() {
        Sensor sensorDTO = new Sensor(1L, "Temperature Sensor", 25.0, "TEMPERATURE");

        SensorEntity sensorEntity = SensorMapper.toSensorEntity(sensorDTO);

        assertNotNull(sensorEntity);
        assertEquals(1L, sensorEntity.getId());
        assertEquals("Temperature Sensor", sensorEntity.getName());
        assertEquals(25.0, sensorEntity.getValue());
        assertEquals(SensorType.TEMPERATURE, sensorEntity.getSensorType());
    }

    @Test
    public void testToSensorEntity_withNullSensorDTO() {

        SensorEntity sensorEntity = SensorMapper.toSensorEntity(null);

        assertNull(sensorEntity);
    }
}
