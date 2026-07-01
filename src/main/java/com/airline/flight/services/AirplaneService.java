package com.airline.flight.services;

import com.airline.flight.dto.airplane.request.CreateAirplaneRequest;
import com.airline.flight.entity.Airplane;

public interface AirplaneService {

    public Airplane createAirplane(CreateAirplaneRequest airplane);
}
