package com.emse.spring.automacorp.api;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

import com.emse.spring.automacorp.record.AddressSearchService;
import com.emse.spring.automacorp.record.ApiGouvAddress;
import java.util.List;

@RestController
@Tag(name = "Address Search Controller", description = "APIs for searching addresses via the French Government Address API")
public class AddressController {

    private final AddressSearchService addressSearchService;

    // Constructor injection for AddressSearchService
    public AddressController(AddressSearchService addressSearchService) {
        this.addressSearchService = addressSearchService;
    }

    // Expose a GET endpoint to search addresses
    @Operation(summary = "Search for addresses", description = "Returns a list of addresses matching the search criteria")
    @GetMapping("/api/search-address")
    public List<ApiGouvAddress> searchAddress(@RequestParam List<String> keywords) {
        // Call the AddressSearchService to perform the search
        return addressSearchService.searchAddress(keywords);
    }
}