package com.airline.flight.controllers.v1;

import com.airline.flight.constants.ApiRoutes;
import com.airline.flight.dto.airport.response.AirportResponse;
import com.airline.flight.dto.city.request.CreateCityRequest;
import com.airline.flight.dto.city.response.CityResponse;
import com.airline.flight.dto.common.ApiResponse;
import com.airline.flight.dto.common.ApiResponseBuilder;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping(ApiRoutes.API_V1 + ApiRoutes.CITIES)
public class CityController {

    private final String route = ApiRoutes.API_V1 + ApiRoutes.CITIES;

    @PostMapping
    public ResponseEntity<ApiResponse<CityResponse>> createCity(
            @Valid @RequestBody CreateCityRequest createCityRequest
    ) {

        CityResponse cityResponse = new CityResponse();

        return ResponseEntity.status(HttpStatus.CREATED).body(
                ApiResponseBuilder.success(
                        HttpStatus.CREATED.value(),
                        "City Created Successfully",
                        cityResponse,
                        route
                )
        );
    }

    // Get All Cities
    @GetMapping
    public ResponseEntity<ApiResponse<List<CityResponse>>> getAllCities() {

        List<CityResponse> cityResponseList = new ArrayList<>();

        return ResponseEntity.status(HttpStatus.OK).body(
                ApiResponseBuilder.success(
                        HttpStatus.OK.value(),
                        "City Created Successfully",
                        cityResponseList,
                        route
                )
        );
    }

    // Get Specific City
    @GetMapping("/{cityId}")
    public ResponseEntity<ApiResponse<CityResponse>> getSpecificCity(@PathVariable UUID cityId) {

        CityResponse cityResponse = new CityResponse();

        return ResponseEntity.status(HttpStatus.OK).body(
                ApiResponseBuilder.success(
                        HttpStatus.OK.value(),
                        "City Fetch Successfully",
                        cityResponse,
                        route + cityId
                )
        );
    }

    // Get City Airports
    @GetMapping("/{cityId}" + ApiRoutes.AIRPORTS)
    public ResponseEntity<ApiResponse<List<AirportResponse>>> getSpecificCityAirports(@PathVariable UUID cityId) {

        List<AirportResponse> airportResponseList = new ArrayList<>();

        return ResponseEntity.status(HttpStatus.OK).body(
                ApiResponseBuilder.success(
                        HttpStatus.OK.value(),
                        "City Airport Fetch Successfully",
                        airportResponseList,
                        route + cityId +  ApiRoutes.AIRPORTS
                )
        );
    }




}
