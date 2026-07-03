package com.airline.flight.mapper;

import com.airline.flight.dto.flight.request.CreateFlightRequest;
import com.airline.flight.dto.flight.response.FlightResponse;
import com.airline.flight.entity.Flight;

public class FlightMapper {

    public static Flight toEntity(CreateFlightRequest createFlightRequest) {

        return Flight.builder()
                .flightNumber(createFlightRequest.getFlightNumber())
                .arrivalTime(createFlightRequest.getArrivalTime())
                .departureTime(createFlightRequest.getDepartureTime())
                .price(createFlightRequest.getPrice())
                .boardingGate(createFlightRequest.getBoardingGate())
                .status(createFlightRequest.getStatus())
                .build();
    }

    public static FlightResponse toResponse(Flight flight) {
        return FlightResponse.builder()
                .id(flight.getId())
                .flightNumber(flight.getFlightNumber())
                .arrivalTime(flight.getArrivalTime())
                .departureTime(flight.getDepartureTime())
                .price(flight.getPrice())
                .boardingGate(flight.getBoardingGate())
                .status(flight.getStatus())
                .createdAt(flight.getCreatedAt())
                .updatedAt(flight.getUpdatedAt())
                .build();
    }
}
