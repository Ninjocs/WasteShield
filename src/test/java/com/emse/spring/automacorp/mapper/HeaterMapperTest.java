package com.emse.spring.automacorp.mapper;

import com.emse.spring.automacorp.model.RoomEntity;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class HeaterMapperTest {

    @Test
    public void testToRecord_withValidHeaterEntity() {
        HeaterEntity heaterEntity = new HeaterEntity();
        heaterEntity.setId(1L);
        heaterEntity.setName("Heater1");

        RoomEntity room = new RoomEntity();
        room.setId(2L);
        heaterEntity.setRoom(room);

        SensorEntity sensor = new SensorEntity();
        sensor.setId(3L);
        heaterEntity.setSensor(sensor);

        Heater heaterRecord = HeaterMapper.toRecord(heaterEntity);

        assertNotNull(heaterRecord);
        assertEquals(1L, heaterRecord.id());
        assertEquals("Heater1", heaterRecord.name());
        assertEquals(2L, heaterRecord.roomId());
        assertEquals(3L, heaterRecord.sensorId());
    }

    @Test
    public void testToRecord_withNullHeaterEntity() {
        Heater heaterRecord = HeaterMapper.toRecord(null);

        assertNull(heaterRecord);
    }

    @Test
    public void testToEntity_withValidHeaterRecord() {
        Heater heaterRecord = new Heater(1L, "Heater1", 2L, 3L);

        RoomEntity room = new RoomEntity();
        room.setId(2L);

        SensorEntity sensor = new SensorEntity();
        sensor.setId(3L);

        HeaterEntity heaterEntity = HeaterMapper.toEntity(heaterRecord, room, sensor);

        assertNotNull(heaterEntity);
        assertEquals(1L, heaterEntity.getId());
        assertEquals("Heater1", heaterEntity.getName());
        assertEquals(2L, heaterEntity.getRoom().getId());
        assertEquals(3L, heaterEntity.getSensor().getId());
    }

    @Test
    public void testToEntity_withNullHeaterRecord() {
        HeaterEntity heaterEntity = HeaterMapper.toEntity(null, null, null);

        assertNull(heaterEntity);
    }
}

