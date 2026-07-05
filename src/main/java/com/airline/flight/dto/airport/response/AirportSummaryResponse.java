package com.airline.flight.dto.airport.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AirportSummaryResponse {

    private UUID id;
    private String name;
    private String code;

}
