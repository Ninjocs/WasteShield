package com.emse.spring.automacorp.dao;

import com.emse.spring.automacorp.model.WindowEntity;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class WindowDaoCustomImpl implements WindowDaoCustom {

    @PersistenceContext
    private EntityManager em;

    @Override
    public List<WindowEntity> findRoomsWithOpenWindows(Long roomId) {
        String jpql = "SELECT w FROM WindowEntity w " +
                "INNER JOIN w.windowStatus s " +
                "WHERE w.room.id = :roomId AND s.value = 1.0 " +  // Assuming 1.0 means 'open'
                "ORDER BY w.name";
        return em.createQuery(jpql, WindowEntity.class)
                .setParameter("roomId", roomId)
                .getResultList();
    }
}

