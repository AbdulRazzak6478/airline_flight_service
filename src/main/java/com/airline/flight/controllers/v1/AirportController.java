package com.airline.flight.controllers.v1;

import com.airline.flight.constants.ApiRoutes;
import com.airline.flight.dto.airport.request.CreateAirportRequest;
import com.airline.flight.dto.airport.response.AirportResponse;
import com.airline.flight.dto.common.ApiResponse;
import com.airline.flight.dto.common.ApiResponseBuilder;
import com.airline.flight.services.AirportService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping(ApiRoutes.API_V1 + ApiRoutes.AIRPORTS)
public class AirportController {

    private final String route =  ApiRoutes.API_V1 + ApiRoutes.AIRPORTS;

    @Autowired
    private AirportService airportService;

    @PostMapping
    public ResponseEntity<ApiResponse<AirportResponse>> createAirport(
            @Valid @RequestBody CreateAirportRequest createAirportRequest
    ) {
        // Call service
        AirportResponse airportResponse = airportService.createAirport(createAirportRequest);

        return ResponseEntity.status(HttpStatus.CREATED).body(
                ApiResponseBuilder.success(
                        HttpStatus.CREATED.value(),
                        "City airport created successfully",
                        airportResponse,
                        route
                )
        );
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<AirportResponse>>> getAllAirports() {

        List<AirportResponse> airportResponseList = airportService.getAllAirports();

        return ResponseEntity.status(HttpStatus.OK).body(
                ApiResponseBuilder.success(
                        HttpStatus.OK.value(),
                        "Fetch All Airports Successfully",
                        airportResponseList,
                        route
                )
        );
    }

    @GetMapping("/{airportId}")
    public ResponseEntity<ApiResponse<AirportResponse>> getSpecificAirport(
            @PathVariable UUID airportId
    ) {
        // Call service
        AirportResponse airportResponse = airportService.getAirport(airportId);

        return ResponseEntity.status(HttpStatus.OK).body(
                ApiResponseBuilder.success(
                        HttpStatus.OK.value(),
                        "City airport fetch successfully",
                        airportResponse,
                        route + airportId
                )
        );
    }

    @DeleteMapping("/{airportId}")
    public ResponseEntity<ApiResponse<String>> deleteSpecificAirport(@PathVariable UUID airportId) {

        airportService.deleteAirport(airportId);

        return ResponseEntity.status(HttpStatus.OK).body(
                ApiResponseBuilder.success(
                        HttpStatus.OK.value(),
                        "City airport deleted successfully",
                        "City Airport Deleted Successfully",
                        route
                )
        );
    }

}
