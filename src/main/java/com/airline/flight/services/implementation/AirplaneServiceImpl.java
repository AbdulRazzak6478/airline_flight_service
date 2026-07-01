package com.airline.flight.services.implementation;

import com.airline.flight.dto.airplane.request.CreateAirplaneRequest;
import com.airline.flight.dto.airplane.response.CreateAirplaneResponse;
import com.airline.flight.entity.Airplane;
import com.airline.flight.exception.DuplicateResourceException;
import com.airline.flight.exception.ResourceNotFoundException;
import com.airline.flight.mapper.AirplaneMapper;
import com.airline.flight.respositories.AirplaneRepository;
import com.airline.flight.services.AirplaneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;


@Service
public class AirplaneServiceImpl implements AirplaneService {

    @Autowired
    private AirplaneRepository airplaneRepository;

    @Override
    public CreateAirplaneResponse createAirplane(CreateAirplaneRequest airplaneRequest) {

        if(airplaneRequest.getSeatCapacity() > 700)
        {
            throw new  IllegalArgumentException("Seat capacity is larger than 700");
        }

        long startTime = System.currentTimeMillis();

        if(airplaneRepository.existsByModelNumber(airplaneRequest.getModelNumber()))
        {
            throw new DuplicateResourceException("Model number is already exist.");
        }

        if(airplaneRepository.existsByRegistrationNumber(airplaneRequest.getRegistrationNumber()))
        {
            throw new DuplicateResourceException("Registration number is already exist.");
        }

        long endTime = System.currentTimeMillis();

        System.out.println("time duration : "+(endTime-startTime));

        // Mapper DTO to Entity
        Airplane airplane = AirplaneMapper.toEntity(airplaneRequest);

        airplane.setRegistrationNumber(airplaneRequest.getRegistrationNumber().toUpperCase());

        // Save to db
        long saveStart =  System.currentTimeMillis();
        Airplane saved =  airplaneRepository.save(airplane);
        long saveEnd =  System.currentTimeMillis();

        System.out.println("Airplane saved time duration : "+(saveStart-saveEnd));

        // Mapper Entity to Response DTO
        return AirplaneMapper.toResponse(saved);
    }

    @Override
    public List<Airplane> getAirplanes() {

        return airplaneRepository.findAll();
    }

    @Override
    public Airplane getAirplaneById(String id) {

        return airplaneRepository.findById(UUID.fromString(id))
                .orElseThrow(()-> new ResourceNotFoundException("Airplane not found"));
    }

    @Override
    public void deleteAirplaneById(String id){

        airplaneRepository.deleteById(UUID.fromString(id));
    }
}
