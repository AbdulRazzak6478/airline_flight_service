package com.airline.flight.services;

import com.airline.flight.dto.airplane.request.CreateAirplaneRequest;
import com.airline.flight.dto.airplane.response.AirplaneResponse;

import java.util.List;

public interface AirplaneService {

    public AirplaneResponse createAirplane(CreateAirplaneRequest airplane);

    public List<AirplaneResponse> getAirplanes();

    public AirplaneResponse  getAirplaneById(String id);

    public void deleteAirplaneById(String id);
}
