package com.airline.flight.services;

import com.airline.flight.dto.flight.request.CreateFlightRequest;
import com.airline.flight.dto.flight.request.UpdateFlightRequest;
import com.airline.flight.dto.flight.response.FlightResponse;

import java.util.List;
import java.util.UUID;

public interface FlightService {

    public FlightResponse createFlight(CreateFlightRequest createFlightRequest);

    public List<FlightResponse> getAllFlights();

    public FlightResponse getFlight(UUID flightId);

    public FlightResponse updateFlightDetails(UUID flightId, UpdateFlightRequest updateFlightRequest);
}
