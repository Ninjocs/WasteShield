package com.emse.spring.automacorp.api;

import com.emse.spring.automacorp.model.RoomEntity;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Tag(name = "Room Controller", description = "APIs for managing rooms")
@RestController
@RequestMapping("/api/places")
public class RoomController {

    private List<RoomEntity> roomList = new ArrayList<>();

    @Operation(summary = "Retrieve a list of rooms", description = "Fetch all rooms in the system")
    @GetMapping
    public List<RoomEntity> getRooms() {
        return roomList;
    }

    @Operation(summary = "Retrieve a room by ID", description = "Find a room using its ID")
    @GetMapping("/{place_id}")
    public Optional<RoomEntity> getRoomById(@PathVariable Long place_id) {
        return roomList.stream().filter(r -> r.getId().equals(place_id)).findFirst();
    }

    @Operation(summary = "Create a room", description = "Create a new room and add it to the system")
    @PostMapping
    public ResponseEntity<RoomEntity> createRoom(@RequestBody RoomEntity roomEntity) {
        // Auto-increment the room_id based on the current maximum room_id in the room list
        Long newRoomId = roomList.stream()
                .mapToLong(RoomEntity::getId)
                .max()
                .orElse(0) + 1; // Set to 1 if no rooms are present

        roomEntity.setId(newRoomId); // Automatically set the new room ID
        roomList.add(roomEntity);     // Add the room to the list
        return ResponseEntity.status(HttpStatus.CREATED).body(roomEntity);
    }

    @Operation(summary = "Delete a room by ID", description = "Delete a room and all associated windows")
    @DeleteMapping("/{place_id}")
    public ResponseEntity<Void> deleteRoom(@PathVariable Long place_id) {
        Optional<RoomEntity> room = roomList.stream().filter(r -> r.getId().equals(place_id)).findFirst();
        if (room.isPresent()) {
            roomList.remove(room.get());
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    // Other RoomController methods...
}
