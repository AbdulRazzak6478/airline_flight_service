package com.airline.flight.controllers.v1;


import com.airline.flight.constants.ApiRoutes;
import com.airline.flight.dto.common.ApiResponse;
import com.airline.flight.dto.common.ApiResponseBuilder;
import com.airline.flight.dto.common.PageResponse;
import com.airline.flight.dto.flight.request.CreateFlightRequest;
import com.airline.flight.dto.flight.request.FlightSearchRequest;
import com.airline.flight.dto.flight.request.UpdateFlightRequest;
import com.airline.flight.dto.flight.response.FlightResponse;
import com.airline.flight.dto.flight.response.FlightWithAssociatesResponse;
import com.airline.flight.services.FlightService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping(ApiRoutes.API_V1 + ApiRoutes.FLIGHTS )
public class FlightController {

    private final String route = ApiRoutes.API_V1 + ApiRoutes.FLIGHTS;

    @Autowired
    private FlightService flightService;

    @PostMapping
    public ResponseEntity<ApiResponse<FlightResponse>> createFlight(
            @Valid @RequestBody CreateFlightRequest createFlightRequest
            ) {

        FlightResponse  flightResponse = flightService.createFlight(createFlightRequest);

        return ResponseEntity.status(HttpStatus.CREATED).body(
                ApiResponseBuilder.success(
                        HttpStatus.CREATED.value(),
                        "Flight Created Successfully",
                        flightResponse,
                        route
                )
        );
    }

    @GetMapping
    public ResponseEntity<ApiResponse<PageResponse<FlightWithAssociatesResponse>>> getFlights(
            @ModelAttribute FlightSearchRequest searchRequest
            ){

        PageResponse<FlightWithAssociatesResponse> flights =  flightService.getFlights(searchRequest);

        System.out.println("getFlights Called");


//        List<FlightResponse>  flightResponseList = flightService.getAllFlights();

        return ResponseEntity.status(HttpStatus.OK).body(
                ApiResponseBuilder.success(
                        HttpStatus.OK.value(),
                        "Flights Fetched Successfully",
                        flights,
                        route
                )
        );
    }

    @GetMapping("/{flightId}")
    public ResponseEntity<ApiResponse<FlightResponse>> getFlight(
            @PathVariable UUID flightId
            ){

        FlightResponse  flightResponse = flightService.getFlight(flightId);

        return ResponseEntity.status(HttpStatus.OK).body(
                ApiResponseBuilder.success(
                        HttpStatus.OK.value(),
                        "Flight Fetch Successfully",
                        flightResponse,
                        route
                )
        );
    }

    // Update Flight Details
    @PatchMapping("/{flightId}")
    public ResponseEntity<ApiResponse<FlightResponse>> updateFlightDetails(
            @PathVariable UUID flightId,
            @Valid @RequestBody UpdateFlightRequest updateFlightRequest
    ){

        FlightResponse  flightResponse = flightService.updateFlightDetails(flightId, updateFlightRequest);

        return ResponseEntity.status(HttpStatus.OK).body(
                ApiResponseBuilder.success(
                        HttpStatus.OK.value(),
                        "Flight Updated Successfully",
                        flightResponse,
                        route
                )
        );
    }
}
