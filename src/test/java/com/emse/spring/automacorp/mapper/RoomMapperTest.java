package com.emse.spring.automacorp.mapper;

import com.emse.spring.automacorp.model.RoomEntity;
import com.emse.spring.automacorp.model.WindowEntity;
import com.emse.spring.automacorp.record.Room;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class RoomMapperTest {

    @Test
    public void testToRoom_withValidRoomEntity() {
        RoomEntity roomEntity = new RoomEntity();
        roomEntity.setId(1L);
        roomEntity.setName("Living Room");
        roomEntity.setFloor(2L);

        WindowEntity window1 = new WindowEntity();
        window1.setId(101L);
        WindowEntity window2 = new WindowEntity();
        window2.setId(102L);

        List<WindowEntity> windows = new ArrayList<>();
        windows.add(window1);
        windows.add(window2);
        roomEntity.setWindows(windows);

        Room roomRecord = RoomMapper.toRoom(roomEntity);

        assertNotNull(roomRecord);
        assertEquals(1L, roomRecord.id());
        assertEquals("Living Room", roomRecord.name());
        assertEquals(2, roomRecord.floor());
        assertEquals(2, roomRecord.windowIds().size());
        assertTrue(roomRecord.windowIds().contains(101L));
        assertTrue(roomRecord.windowIds().contains(102L));
    }

    @Test
    public void testToRoom_withNullRoomEntity() {
        Room roomRecord = RoomMapper.toRoom(null);

        assertNull(roomRecord);
    }

    @Test
    public void testToRoom_withNullWindows() {
        RoomEntity roomEntity = new RoomEntity();
        roomEntity.setId(1L);
        roomEntity.setName("Living Room");
        roomEntity.setFloor(2L);
        roomEntity.setWindows(new ArrayList<>());

        Room roomRecord = RoomMapper.toRoom(roomEntity);

        assertNotNull(roomRecord);
        assertEquals(1L, roomRecord.id());
        assertNull(roomRecord.windowIds());
    }
}