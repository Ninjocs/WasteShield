package com.emse.spring.automacorp.mapper;

import com.emse.spring.automacorp.model.WindowEntity;
import com.emse.spring.automacorp.model.RoomEntity;
import com.emse.spring.automacorp.record.Window;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class WindowMapperTest {

    @Test
    public void testToWindowDTO_withValidWindowEntity() {
        WindowEntity windowEntity = new WindowEntity();
        windowEntity.setId(1L);
        windowEntity.setName("Living Room Window");
        windowEntity.setWindowStatus(1.0);

        RoomEntity room = new RoomEntity();
        room.setId(101L);
        windowEntity.setRoom(room);

        Window windowDTO = WindowMapper.toWindowDTO(windowEntity);

        assertNotNull(windowDTO);
        assertEquals(1L, windowDTO.id());
        assertEquals("Living Room Window", windowDTO.name());
        assertEquals(1.0, windowDTO.windowStatus().getValue());
        assertEquals(101L, windowDTO.roomId());
    }

    @Test
    public void testToWindowDTO_withNullWindowEntity() {
        Window windowDTO = WindowMapper.toWindowDTO(null);

        assertNull(windowDTO);
    }

    @Test
    public void testToWindowEntity_withValidWindowDTO() {
        SensorEntity sensor = new SensorEntity();
        sensor.setValue(0.0);
        Window windowDTO = new Window(1L, "Living Room Window", sensor, 101L);  // Ensure this is a string

        WindowEntity windowEntity = WindowMapper.toWindowEntity(windowDTO);

        assertNotNull(windowEntity);
        assertEquals(1L, windowEntity.getId());
        assertEquals("Living Room Window", windowEntity.getName());
        assertEquals("CLOSED", windowEntity.getWindowStatus().getValue() == 0.0 ? "CLOSED" : "OPEN"); // Ensure this logic matches your value setting
    }

    @Test
    public void testToWindowEntity_withNullWindowDTO() {
        WindowEntity windowEntity = WindowMapper.toWindowEntity(null);

        assertNull(windowEntity);
    }
}
