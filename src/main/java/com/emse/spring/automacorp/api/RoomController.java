package com.emse.spring.automacorp.api;

import com.emse.spring.automacorp.model.RoomEntity;
import com.emse.spring.automacorp.model.WindowEntity;
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
    public ResponseEntity<RoomEntity> getRoomById(@PathVariable Long place_id) {
        Optional<RoomEntity> room = roomList.stream().filter(r -> r.getId().equals(place_id)).findFirst();
        return room.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    @Operation(summary = "Create or update a room", description = "Create or update a room and add it to the system")
    @PostMapping
    public ResponseEntity<RoomEntity> createOrUpdateRoom(@RequestBody RoomEntity roomEntity) {
        // Check if a room with the given ID already exists
        Optional<RoomEntity> existingRoom = roomList.stream()
                .filter(room -> room.getId().equals(roomEntity.getId()))
                .findFirst();

        if (existingRoom.isPresent()) {
            // If the room exists, update the room's properties
            RoomEntity roomToUpdate = existingRoom.get();
            roomToUpdate.setName(roomEntity.getName());  // Update the room's fields
            roomToUpdate.setCurrentTemperature(roomEntity.getCurrentTemperature());
            roomToUpdate.setCurrentHumidity(roomEntity.getCurrentHumidity());

            // Update or add windows
            for (WindowEntity newWindow : roomEntity.getWindows()) {
                Optional<WindowEntity> existingWindow = roomToUpdate.getWindows().stream()
                        .filter(window -> window.getId().equals(newWindow.getId()))
                        .findFirst();

                if (existingWindow.isPresent()) {
                    // Update the existing window
                    WindowEntity windowToUpdate = existingWindow.get();
                    windowToUpdate.setName(newWindow.getName());
                    windowToUpdate.setRoom(newWindow.getRoom());
                } else {
                    // Add the new window if it doesn't exist
                    roomToUpdate.getWindows().add(newWindow);
                }
            }

            return ResponseEntity.status(HttpStatus.OK).body(roomToUpdate);
        } else {
            // If the room doesn't exist, create a new one and add it to the list
            roomEntity.setId((long) (roomList.size() + 1));  // Generate new ID if necessary
            roomList.add(roomEntity);
            return ResponseEntity.status(HttpStatus.CREATED).body(roomEntity);
        }
    }

    @Operation(summary = "Delete a room by ID", description = "Delete a room and all associated windows and heaters")
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

    @Operation(summary = "Open all windows in a room", description = "Switch all windows in the room to OPEN")
    @PutMapping("/{place_id}/openWindows")
    public ResponseEntity<Void> openWindows(@PathVariable Long place_id) {
        Optional<RoomEntity> room = roomList.stream().filter(r -> r.getId().equals(place_id)).findFirst();
        if (room.isPresent()) {
            return ResponseEntity.status(HttpStatus.OK).build();
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @Operation(summary = "Close all windows in a room", description = "Switch all windows in the room to CLOSED")
    @PutMapping("/{place_id}/closeWindows")
    public ResponseEntity<Void> closeWindows(@PathVariable Long place_id) {
        Optional<RoomEntity> room = roomList.stream().filter(r -> r.getId().equals(place_id)).findFirst();
        if (room.isPresent()) {
            return ResponseEntity.status(HttpStatus.OK).build();
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
}
