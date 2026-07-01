package com.airline.flight.controllers.v1;

import com.airline.flight.constants.ApiRoutes;
import com.airline.flight.dto.airplane.request.CreateAirplaneRequest;
import com.airline.flight.dto.common.ApiResponse;
import com.airline.flight.entity.Airplane;
import com.airline.flight.services.AirplaneService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping(ApiRoutes.API_V1+ApiRoutes.AIRPLANES)
@Validated
public class AirplaneController {


    @Autowired
    private AirplaneService airplaneService;

    @PostMapping
    public ResponseEntity<?> addAirplane(
           @Valid @RequestBody CreateAirplaneRequest airplaneRequest
            ){

        Airplane newAirplane = airplaneService.createAirplane(airplaneRequest);
        System.out.println("Created a New Airplane");
        ApiResponse<Airplane> response = ApiResponse.<Airplane>builder()
                .success(true)
                .status(HttpStatus.OK.value())
                .message("Successfully Created Airplane")
                .data(newAirplane)
                .errors(Collections.emptyList())
                .timestamp(LocalDateTime.now())
                .path(ApiRoutes.API_V1+ApiRoutes.AIRPLANES)
                .traceId("Testing correlation id")
                .build();

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    // Get All Airplanes
    @GetMapping
    public ResponseEntity<ApiResponse<List<Airplane>>> getAllAirplanes (){

        List<Airplane> airplanes = new ArrayList<Airplane>();

        ApiResponse<List<Airplane>> response = ApiResponse.<List<Airplane>>builder()
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
    public ResponseEntity<ApiResponse<Airplane>> getAirplane(
           @PathVariable @NotBlank @Size(min=36,max=36) UUID id
    ){
        Airplane airplane = new Airplane();

        ApiResponse<Airplane> response = ApiResponse.<Airplane>builder()
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
}
