package com.emse.spring.automacorp.model;

import com.fasterxml.jackson.annotation.JsonBackReference;  // Jackson annotation for managing back reference
import jakarta.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "SP_MEAL")
public class WindowEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotNull
    private String name;

    @ManyToOne
    @JoinColumn(name = "place_id")
    @JsonBackReference  // This annotation prevents infinite recursion by excluding the 'room' field from serialization
    private RoomEntity room;

    public WindowEntity() {}

    public WindowEntity(String name, RoomEntity room) {
        this.name = name;
        this.room = room;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public RoomEntity getRoom() {
        return room;
    }

    public void setRoom(RoomEntity room) {
        this.room = room;
    }
}
