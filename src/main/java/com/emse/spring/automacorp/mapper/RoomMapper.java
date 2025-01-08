package com.emse.spring.automacorp.mapper;

import com.emse.spring.automacorp.record.Room;
import com.emse.spring.automacorp.model.RoomEntity;
import com.emse.spring.automacorp.model.WindowEntity;

import java.util.List;
import java.util.stream.Collectors;

public class RoomMapper {

    public static Room toRoom(RoomEntity roomEntity) {
        if (roomEntity == null) {
            return null;
        }

        List<Long> windowIds = (roomEntity.getWindows() != null && !roomEntity.getWindows().isEmpty())
                ? roomEntity.getWindows().stream()
                .map(WindowEntity::getId)
                .collect(Collectors.toList())
                : null;

        return new Room(
                roomEntity.getId(),
                roomEntity.getName(),
                roomEntity.getCurrentHumidity(),
                roomEntity.getCurrentTemperature(),
                windowIds
        );
    }

    public static RoomEntity toRoomEntity(Room roomDTO) {
        RoomEntity roomEntity = new RoomEntity();
        roomEntity.setId(roomDTO.id());
        roomEntity.setName(roomDTO.name());
        roomEntity.setCurrentTemperature(roomDTO.currentTemperature());
        roomEntity.setCurrentHumidity(roomDTO.currentHumidity());

        return roomEntity;
    }
}
