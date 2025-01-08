package com.emse.spring.automacorp.model;

import jakarta.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "SP_PLACE")
public class RoomEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull // Non-nullable name
    private String name;

    @Column(name = "current_temperature")
    private Long currentTemperature;

    @Column(name = "current_humidity")
    private Double currentHumidity;

    @OneToMany(mappedBy = "room", cascade = CascadeType.ALL)
    private List<WindowEntity> windows;

    public RoomEntity() {}

    public RoomEntity(String name, Double targetTemperature,Double currentHumidity, List<WindowEntity> windows) {
        this.name = name;
        this.currentTemperature = currentTemperature;
        this.currentHumidity = currentHumidity;
        this.windows = windows;

        for (WindowEntity window : windows) {
            window.setRoom(this);
        }
    }

    public RoomEntity(String name, Long currentTemperature, double humidity) {
        this.name = name;
        this.currentTemperature = currentTemperature;
        this.currentHumidity = humidity;

        for (WindowEntity window : windows) {
            window.setRoom(this);
        }
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

    public Long getCurrentTemperature() {
        return currentTemperature;
    }

    public void setCurrentTemperature(Long currentTemperature) {
        this.currentTemperature = currentTemperature;
    }

    public Double getCurrentHumidity() {
        return currentHumidity;
    }

    public void setCurrentHumidity(Double targetTemperature) {
        this.currentHumidity = targetTemperature;
    }

    public List<WindowEntity> getWindows() {
        return windows;
    }

    public void setWindows(List<WindowEntity> windows) {
        this.windows = windows;

        for (WindowEntity window : windows) {
            window.setRoom(this);
        }
    }
}

