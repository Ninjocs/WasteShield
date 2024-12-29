package com.emse.spring.automacorp.record;

import java.util.List;

public record Room(
        Long id,
        String name,
        Double currentHumidity,
        Long currentTemperature,
        List<Long> windowIds
) {}