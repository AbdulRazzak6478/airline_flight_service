package com.airline.flight.services;

import com.airline.flight.dto.airplane.request.CreateAirplaneRequest;
import com.airline.flight.dto.airplane.request.CreateAirplaneSeatRequest;
import com.airline.flight.dto.airplane.response.AirplaneResponse;
import com.airline.flight.dto.airplane.response.AirplaneSeatResponse;

import java.util.*;

public interface AirplaneSeatService {

    public AirplaneSeatResponse createAirplaneSeat(UUID airplaneId, CreateAirplaneSeatRequest airplaneSeat);

    public List<AirplaneSeatResponse> getAirplaneSeats(UUID  airplaneId);

    public AirplaneSeatResponse  getAirplaneById(String id);

    public void deleteAirplaneSeat(String id);
}
