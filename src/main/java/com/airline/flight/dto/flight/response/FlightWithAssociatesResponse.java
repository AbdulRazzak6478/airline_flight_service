package com.airline.flight.dto.flight.response;

import com.airline.flight.dto.airplane.response.AirplaneSummaryResponse;
import com.airline.flight.dto.airport.response.AirportSummaryResponse;
import com.airline.flight.enums.FlightStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FlightWithAssociatesResponse {

    private UUID id;

    private String flightNumber;

    private LocalDateTime departureTime;
    private LocalDateTime arrivalTime;
    private BigDecimal price;

    private String boardingGate;
    private FlightStatus status;

    private AirplaneSummaryResponse airplane;

    private AirportSummaryResponse departureAirport;

    private AirportSummaryResponse arrivalAirport;
}
