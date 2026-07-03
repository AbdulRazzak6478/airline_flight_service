package com.airline.flight.controllers.v1;

import com.airline.flight.constants.ApiRoutes;
import com.airline.flight.dto.airport.request.CreateAirportRequest;
import com.airline.flight.dto.airport.response.AirportResponse;
import com.airline.flight.dto.common.ApiResponse;
import com.airline.flight.dto.common.ApiResponseBuilder;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(ApiRoutes.API_V1 + ApiRoutes.AIRPORTS)
public class AirportController {

    private final String route =  ApiRoutes.API_V1 + ApiRoutes.AIRPORTS;
    @PostMapping
    public ResponseEntity<ApiResponse<AirportResponse>> createAirport(
            @Valid @RequestBody CreateAirportRequest createAirportRequest
    ) {
        AirportResponse airportResponse = new AirportResponse();
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

        List<AirportResponse> airportResponseList = new ArrayList<>();

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
    public ResponseEntity<ApiResponse<AirportResponse>> getSpecificAirport(@PathVariable String airportId) {
        AirportResponse airportResponse = new AirportResponse();

        return ResponseEntity.status(HttpStatus.CREATED).body(
                ApiResponseBuilder.success(
                        HttpStatus.CREATED.value(),
                        "City airport fetch successfully",
                        airportResponse,
                        route + airportId
                )
        );
    }

    @DeleteMapping("/{airportId}")
    public ResponseEntity<ApiResponse<String>> deleteSpecificAirport(@PathVariable String airportId) {

        return ResponseEntity.status(HttpStatus.CREATED).body(
                ApiResponseBuilder.success(
                        HttpStatus.CREATED.value(),
                        "City airport deleted successfully",
                        "City Airport Deleted Successfully",
                        route
                )
        );
    }

}
