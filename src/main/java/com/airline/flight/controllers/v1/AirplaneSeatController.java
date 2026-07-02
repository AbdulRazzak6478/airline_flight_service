package com.airline.flight.controllers.v1;

import com.airline.flight.constants.ApiRoutes;
import com.airline.flight.dto.airplane.request.CreateAirplaneSeatRequest;
import com.airline.flight.dto.airplane.response.AirplaneSeatResponse;
import com.airline.flight.dto.common.ApiResponse;
import com.airline.flight.dto.common.ApiResponseBuilder;
import com.airline.flight.services.AirplaneSeatService;
import com.airline.flight.services.AirplaneService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping(
        ApiRoutes.API_V1 + ApiRoutes.AIRPLANES + ApiRoutes.AIRPLANE_ID + ApiRoutes.AIRPLANE_SEATS
)
public class AirplaneSeatController {

    @Autowired
    private AirplaneSeatService airplaneSeatService;

    @PostMapping()
    public ResponseEntity<ApiResponse<AirplaneSeatResponse>> getAirplaneSeats(
            @PathVariable UUID airplaneId,
            @Valid @RequestBody CreateAirplaneSeatRequest airplaneSeatRequest
            ) {


        // Call Airplane seat Service
        AirplaneSeatResponse response = airplaneSeatService.createAirplaneSeat(airplaneId, airplaneSeatRequest);


        return ResponseEntity.status(HttpStatus.CREATED).body(
                ApiResponseBuilder.success(
                        HttpStatus.CREATED.value(),
                        "Airplane Seat Created Successfully",
                        response,
                        ApiRoutes.API_V1 + ApiRoutes.AIRPLANES + ApiRoutes.AIRPLANE_ID + ApiRoutes.AIRPLANE_SEATS
                )
        );
    }

    @GetMapping()
    public ResponseEntity<ApiResponse<List<AirplaneSeatResponse>>> getAirplaneSeats(@PathVariable UUID airplaneId) {

        // Call the service
        List<AirplaneSeatResponse> airplaneSeats = airplaneSeatService.getAirplaneSeats(airplaneId);

        // Return Response DTO
        return ResponseEntity.status(HttpStatus.OK).body(
                ApiResponseBuilder.success(
                        HttpStatus.OK.value(),
                        "Airplane seats Fetched Successfully",
                        airplaneSeats,
                        ApiRoutes.API_V1 + ApiRoutes.AIRPLANES + ApiRoutes.AIRPLANE_ID + ApiRoutes.AIRPLANE_SEATS
                )
        );
    }

    @GetMapping("/{seatId}")
    public ResponseEntity<ApiResponse<AirplaneSeatResponse>> getAirplaneSeat(@PathVariable UUID airplaneId,@PathVariable UUID seatId) {

        // Call the service
        AirplaneSeatResponse airplaneSeat = airplaneSeatService.getAirplaneById(seatId.toString());

        // Return Response DTO
        return ResponseEntity.status(HttpStatus.OK).body(
                ApiResponseBuilder.success(
                        HttpStatus.OK.value(),
                        "Airplane seats Fetched Successfully",
                        airplaneSeat,
                        ApiRoutes.API_V1 + ApiRoutes.AIRPLANES + ApiRoutes.AIRPLANE_ID + ApiRoutes.AIRPLANE_SEATS
                )
        );
    }

    @DeleteMapping("/{seatId}")
    public ResponseEntity<ApiResponse<String>> deleteAirplaneSeat(@PathVariable UUID seatId) {

        // Call the service
        airplaneSeatService.deleteAirplaneSeat(seatId.toString());

        // Return Response DTO
        return ResponseEntity.status(HttpStatus.OK).body(
                ApiResponseBuilder.success(
                        HttpStatus.OK.value(),
                        "Airplane seats Fetched Successfully",
                        "Airplane Seat Remove Successfully",
                        ApiRoutes.API_V1 + ApiRoutes.AIRPLANES + ApiRoutes.AIRPLANE_ID + ApiRoutes.AIRPLANE_SEATS
                )
        );
    }

}
