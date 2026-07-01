package com.airline.flight.dto.airplane.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CreateAirplaneResponse {

    private UUID id;

    private String modelNumber;

    private String manufacturer;

    private String registrationNumber;

    private Integer seatCapacity;
}
