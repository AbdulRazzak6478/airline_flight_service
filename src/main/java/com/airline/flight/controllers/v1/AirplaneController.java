package com.airline.flight.controllers.v1;

import com.airline.flight.constants.ApiRoutes;
import com.airline.flight.dto.airplane.request.CreateAirplaneRequest;
import com.airline.flight.dto.airplane.response.AirplaneResponse;
import com.airline.flight.dto.common.ApiResponse;
import com.airline.flight.dto.common.ApiResponseBuilder;
import com.airline.flight.entity.Airplane;
import com.airline.flight.services.AirplaneService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping(ApiRoutes.API_V1+ApiRoutes.AIRPLANES)
@Validated
public class AirplaneController {


    @Autowired
    private AirplaneService airplaneService;

    @PostMapping
    public ResponseEntity<ApiResponse<AirplaneResponse>> addAirplane(
           @Valid @RequestBody CreateAirplaneRequest airplaneRequest
            ){

        AirplaneResponse newAirplaneResponse = airplaneService.createAirplane(airplaneRequest);
        System.out.println("Created a New Airplane");

        return ResponseEntity.status(HttpStatus.CREATED).body(
                ApiResponseBuilder.success(
                    HttpStatus.CREATED.value(),
                        "Successfully Created Airplane",
                        newAirplaneResponse,
                            ApiRoutes.API_V1+ApiRoutes.AIRPLANES
        ));
    }

    // Get All Airplanes
    @GetMapping
    public ResponseEntity<ApiResponse<List<AirplaneResponse>>> getAllAirplanes (){

        List<AirplaneResponse> airplanes = airplaneService.getAirplanes();

        ApiResponse<List<AirplaneResponse>> response = ApiResponse.<List<AirplaneResponse>>builder()
                .success(true)
                .status(HttpStatus.OK.value())
                .message("Successfully Fetched Airplanes")
                .data(airplanes)
                .errors(Collections.emptyList())
                .timestamp(LocalDateTime.now())
                .path(ApiRoutes.API_V1+ApiRoutes.AIRPLANES)
                .traceId("Testing correlation id")
                .build();

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<AirplaneResponse>> getAirplane(
           @PathVariable @NotBlank @Size(min=36,max=36) String id
    ){
        AirplaneResponse
                airplane = airplaneService.getAirplaneById(id);

        ApiResponse<AirplaneResponse> response = ApiResponse.<AirplaneResponse>builder()
                .success(true)
                .status(HttpStatus.OK.value())
                .message("Successfully Fetched Airplane")
                .data(airplane)
                .errors(Collections.emptyList())
                .timestamp(LocalDateTime.now())
                .path(ApiRoutes.API_V1+ApiRoutes.AIRPLANES+"/"+id)
                .traceId("Testing correlation id")
                .build();

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<String>> deleteAirplane(
            @PathVariable @NotBlank @Size(min=36,max=36) String id
    ){
        airplaneService.deleteAirplaneById(id);

        ApiResponse<String> response = ApiResponse.<String>builder()
                .success(true)
                .status(HttpStatus.OK.value())
                .message("Successfully Fetched Airplane")
                .data("Airplane deleted Successfully")
                .errors(Collections.emptyList())
                .timestamp(LocalDateTime.now())
                .path(ApiRoutes.API_V1+ApiRoutes.AIRPLANES+"/"+id)
                .traceId("Testing correlation id")
                .build();

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
}
