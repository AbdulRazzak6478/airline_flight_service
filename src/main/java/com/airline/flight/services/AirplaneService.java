package com.airline.flight.services;

import com.airline.flight.dto.airplane.request.CreateAirplaneRequest;
import com.airline.flight.dto.airplane.response.CreateAirplaneResponse;
import com.airline.flight.entity.Airplane;

import java.util.List;

public interface AirplaneService {

    public CreateAirplaneResponse createAirplane(CreateAirplaneRequest airplane);

    public List<Airplane> getAirplanes();

    public Airplane  getAirplaneById(String id);

//    public Airplane updateAirplaneById(UpdateAirplaneRequest airplane);

    public void deleteAirplaneById(String id);
}
