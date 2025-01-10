package com.emse.spring.automacorp.repository;

import com.emse.spring.automacorp.model.RoomEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface RoomRepository extends JpaRepository<RoomEntity, Long> {
    Optional<RoomEntity> findByName(String name);
}

