package com.airline.flight.mapper;

import com.airline.flight.dto.airport.request.CreateAirportRequest;
import com.airline.flight.dto.airport.response.AirportResponse;
import com.airline.flight.dto.airport.response.AirportSummaryResponse;
import com.airline.flight.entity.Airport;
import org.springframework.stereotype.Component;

@Component
public class AirportMapper {

    public static Airport toEntity(CreateAirportRequest createAirportRequest) {
        Airport airport = new Airport();

        airport.setName(createAirportRequest.getName());
        airport.setCode(createAirportRequest.getCode());
        airport.setAddress(createAirportRequest.getAddress());
        airport.setLatitude(createAirportRequest.getLatitude());
        airport.setLongitude(createAirportRequest.getLongitude());
        return airport;
    }

    public static AirportResponse toResponse(Airport airport) {
        AirportResponse airportResponse = new AirportResponse();

        airportResponse.setId(airport.getId());
        airportResponse.setName(airport.getName());
        airportResponse.setCode(airport.getCode());
        airportResponse.setLongitude(airport.getLongitude());
        airportResponse.setLatitude(airport.getLatitude());
        airportResponse.setAddress(airport.getAddress());
        airportResponse.setCreatedAt(airport.getCreatedAt());
        airportResponse.setUpdatedAt(airport.getUpdatedAt());
        return airportResponse;
    }

    public static AirportSummaryResponse toSummaryResponse(Airport airport) {

        return AirportSummaryResponse.builder()
                .id(airport.getId())
                .name(airport.getName())
                .code(airport.getCode())
                .build();
    }
}
