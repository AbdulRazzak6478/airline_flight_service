package com.airline.flight.services;

import com.airline.flight.dto.common.PageResponse;
import com.airline.flight.dto.flight.request.CreateFlightRequest;
import com.airline.flight.dto.flight.request.FlightSearchRequest;
import com.airline.flight.dto.flight.request.UpdateFlightRequest;
import com.airline.flight.dto.flight.response.FlightResponse;
import com.airline.flight.dto.flight.response.FlightWithAssociatesResponse;

import java.util.List;
import java.util.UUID;

public interface FlightService {

    public FlightResponse createFlight(CreateFlightRequest createFlightRequest);

    public List<FlightResponse> getAllFlights();

    // With Pagination , sorting, filters
    public PageResponse<FlightWithAssociatesResponse> getFlights(FlightSearchRequest searchRequest);

    public FlightResponse getFlight(UUID flightId);

    public FlightResponse updateFlightDetails(UUID flightId, UpdateFlightRequest updateFlightRequest);
}
