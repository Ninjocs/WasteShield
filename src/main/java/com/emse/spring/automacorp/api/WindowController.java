package com.emse.spring.automacorp.api;

import com.emse.spring.automacorp.model.RoomEntity;
import com.emse.spring.automacorp.model.WindowEntity;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Tag(name = "Meal Controller", description = "APIs for managing meals (windows)")
@RestController
@RequestMapping("/api/places/{place_id}")
public class WindowController {

    private final RoomController roomController;

    public WindowController(RoomController roomController) {
        this.roomController = roomController;
    }

    @Operation(summary = "Add a meal (window) to a room", description = "Adds a new meal (window) to a specific room")
    @PostMapping("/windows")
    public ResponseEntity<WindowEntity> addMealToRoom(@PathVariable Long place_id, @RequestParam String name) {
        // Retrieve the room based on place_id
        Optional<RoomEntity> roomOpt = roomController.getRoomById(place_id);
        if (!roomOpt.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build(); // Room not found
        }

        RoomEntity room = roomOpt.get();

        // Auto-increment the meal_id based on the current maximum meal_id in the room's windows list
        Long newMealId = room.getWindows().stream()
                .mapToLong(WindowEntity::getId)
                .max()
                .orElse(0) + 1; // Set to 1 if no windows are present

        // Create a new window (meal) and associate it with the room
        WindowEntity newWindow = new WindowEntity(name, room);
        newWindow.setId(newMealId); // Automatically set the meal_id

        room.getWindows().add(newWindow); // Add window to the room's list of windows

        return ResponseEntity.status(HttpStatus.CREATED).body(newWindow); // Return the created window
    }

    @Operation(summary = "Delete a meal (window) from a room", description = "Deletes a specific meal (window) from the room")
    @DeleteMapping("/windows/{meal_id}")
    public ResponseEntity<Void> deleteMealFromRoom(@PathVariable Long place_id, @PathVariable Long meal_id) {
        // Retrieve the room based on place_id
        Optional<RoomEntity> roomOpt = roomController.getRoomById(place_id);
        if (!roomOpt.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build(); // Room not found
        }

        RoomEntity room = roomOpt.get();

        // Find and remove the window (meal) based on meal_id
        Optional<WindowEntity> windowOpt = room.getWindows().stream()
                .filter(window -> window.getId().equals(meal_id))
                .findFirst();

        if (!windowOpt.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build(); // Meal not found
        }

        // Remove the meal from the room's list of windows
        room.getWindows().remove(windowOpt.get());
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build(); // Successfully deleted
    }

    @Operation(summary = "Fetch all meals (windows) in a room", description = "Fetches all meals (windows) associated with a room")
    @GetMapping("/windows")
    public ResponseEntity<List<WindowEntity>> getAllMealsInRoom(@PathVariable Long place_id) {
        // Retrieve the room based on place_id
        Optional<RoomEntity> roomOpt = roomController.getRoomById(place_id);
        if (!roomOpt.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build(); // Room not found
        }

        RoomEntity room = roomOpt.get();

        // Return the list of meals (windows) for the room
        List<WindowEntity> windows = room.getWindows();
        return ResponseEntity.ok(windows); // Successfully return the list of windows
    }
}
