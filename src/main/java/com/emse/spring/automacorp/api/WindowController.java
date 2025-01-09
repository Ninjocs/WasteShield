package com.emse.spring.automacorp.api;

import com.emse.spring.automacorp.model.RoomEntity;
import com.emse.spring.automacorp.model.WindowEntity;
import com.emse.spring.automacorp.repository.RoomRepository;
import com.emse.spring.automacorp.repository.WindowRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/meal")
public class WindowController {

    @Autowired
    private RoomRepository roomRepository;

    @Autowired
    private WindowRepository windowRepository;

    // Add a meal (window) to an existing room
    @PostMapping
    public ResponseEntity<WindowEntity> addMealToRoom(@RequestBody WindowEntity meal) {
        if (meal.getRoom() == null || meal.getRoom().getName() == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null); // Room name is missing
        }

        // Check if the room exists
        Optional<RoomEntity> roomOpt = roomRepository.findByName(meal.getRoom().getName());
        if (roomOpt.isPresent()) {
            RoomEntity room = roomOpt.get();

            // Link meal to the room
            meal.setRoom(room);

            // Save the meal
            WindowEntity savedMeal = windowRepository.save(meal);

            // Save the updated room with the new meal
            room.getWindows().add(savedMeal);
            roomRepository.save(room);

            return ResponseEntity.status(HttpStatus.CREATED).body(savedMeal);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null); // Room not found
        }
    }

    // Get meals by room
    @GetMapping("/{roomId}/meals")
    public ResponseEntity<?> getMealsByRoom(@PathVariable Long roomId) {
        Optional<RoomEntity> roomOpt = roomRepository.findById(roomId);
        if (roomOpt.isPresent()) {
            return ResponseEntity.ok(roomOpt.get().getWindows());
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Room not found");
        }
    }

    // Delete a meal
    @DeleteMapping("/{mealId}")
    public ResponseEntity<?> deleteMeal(@PathVariable Long mealId) {
        Optional<WindowEntity> mealOpt = windowRepository.findById(mealId);
        if (mealOpt.isPresent()) {
            WindowEntity meal = mealOpt.get();

            // Remove the meal from its room
            RoomEntity room = meal.getRoom();
            room.getWindows().remove(meal);
            roomRepository.save(room);

            // Delete the meal
            windowRepository.delete(meal);

            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Meal not found");
        }
    }
}
