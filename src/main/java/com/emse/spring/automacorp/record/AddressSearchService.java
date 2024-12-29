package com.emse.spring.automacorp.record;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AddressSearchService {

    private final RestTemplate restTemplate;

    // Constructor to initialize RestTemplate
    public AddressSearchService() {
        this.restTemplate = new RestTemplate();
    }

    // Method to search addresses and return a list of ApiGouvAddressDto
    public List<ApiGouvAddress> searchAddress(List<String> keys) {
        // Build the query parameters
        String params = String.join("+", keys);
        String uri = UriComponentsBuilder.fromUriString("https://api-adresse.data.gouv.fr/search")
                .queryParam("q", params)
                .queryParam("limit", 15)
                .build()
                .toUriString();

        // Perform the GET request and retrieve the response
        ApiGouvResponse response = restTemplate.getForObject(uri, ApiGouvResponse.class);

        // Return the list of ApiGouvAddressDto from the features' properties
        return response != null ?
                response.features().stream()
                        .map(ApiGouvFeature::properties)
                        .collect(Collectors.toList())
                : List.of();
    }
}


