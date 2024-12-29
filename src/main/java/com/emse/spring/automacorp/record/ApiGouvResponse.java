package com.emse.spring.automacorp.record;
import java.util.List;

public record ApiGouvResponse(
        String version,
        String query,
        Integer limit,
        List<ApiGouvFeature> features
) {

}
