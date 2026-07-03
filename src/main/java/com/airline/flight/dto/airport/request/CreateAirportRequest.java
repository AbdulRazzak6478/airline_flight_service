package com.airline.flight.dto.airport.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CreateAirportRequest {

    @NotBlank( message ="Code is required.")
    private String code;

    @NotBlank( message ="Address is required.")
    private String address;

    @NotNull( message ="Latitude is required.")
    private Double latitude;

    @NotNull( message ="Longitude is required.")
    private Double longitude;

    @NotNull( message = "CityId is required.")
    private UUID cityId;
}
