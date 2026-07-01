package com.airline.flight.services.implementation;

import com.airline.flight.dto.airplane.request.CreateAirplaneRequest;
import com.airline.flight.entity.Airplane;
import com.airline.flight.services.AirplaneService;
import org.springframework.stereotype.Service;


@Service
public class AirplaneServiceImpl implements AirplaneService {

    @Override
    public Airplane createAirplane(CreateAirplaneRequest airplane) {
        Airplane newAirplane = new Airplane();
        return new Airplane();
    }
}
