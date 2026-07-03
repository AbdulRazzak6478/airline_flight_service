package com.airline.flight.services;

import com.airline.flight.dto.airport.request.CreateAirportRequest;
import com.airline.flight.dto.airport.response.AirportResponse;

import java.util.List;
import java.util.UUID;

public interface AirportService {

    public AirportResponse createAirport(CreateAirportRequest createAirportRequest);

    public List<AirportResponse> getAllAirports();

    public AirportResponse getAirport(UUID airportId);

    public void deleteAirport(UUID airportId);
}
