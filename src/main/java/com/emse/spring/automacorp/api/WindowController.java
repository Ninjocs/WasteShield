package com.emse.spring.automacorp.api;

import com.emse.spring.automacorp.model.WindowEntity;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Tag(name = "Meal Controller", description = "APIs for managing meals")
@RestController
@RequestMapping("/api/meal")
public class WindowController {

    private List<WindowEntity> windowList = new ArrayList<>();

    @Operation(summary = "Retrieve a list of meals", description = "Fetch all meals in the system")
    @GetMapping
    public List<WindowEntity> getWindows() {
        return windowList;
    }

    @Operation(summary = "Retrieve a meal by ID", description = "Find a meal using its ID")
    @GetMapping("/{id}")
    public ResponseEntity<WindowEntity> getWindowById(@PathVariable Long id) {
        Optional<WindowEntity> window = windowList.stream().filter(w -> w.getId().equals(id)).findFirst();
        return window.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    @Operation(summary = "Create a new meal", description = "Create a new meal and add it to the system")
    @PostMapping
    public ResponseEntity<WindowEntity> createWindow(@RequestBody WindowEntity windowEntity) {
        windowEntity.setId((long) (windowList.size() + 1)); // Simple ID assignment for demo
        windowList.add(windowEntity);
        return ResponseEntity.status(HttpStatus.CREATED).body(windowEntity);
    }

    @Operation(summary = "Update an existing meal", description = "Update the details of an existing meal")
    @PutMapping("/{id}")
    public ResponseEntity<WindowEntity> updateWindow(@PathVariable Long id, @RequestBody WindowEntity updatedWindow) {
        Optional<WindowEntity> existingWindow = windowList.stream().filter(w -> w.getId().equals(id)).findFirst();
        if (existingWindow.isPresent()) {
            WindowEntity window = existingWindow.get();
            return ResponseEntity.ok(window);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @Operation(summary = "Delete a meal by ID", description = "Delete a meal using its ID")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteWindow(@PathVariable Long id) {
        Optional<WindowEntity> window = windowList.stream().filter(w -> w.getId().equals(id)).findFirst();
        if (window.isPresent()) {
            windowList.remove(window.get());
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
}