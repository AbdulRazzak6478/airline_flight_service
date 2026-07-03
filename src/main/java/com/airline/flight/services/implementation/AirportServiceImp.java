package com.airline.flight.services.implementation;


import com.airline.flight.dto.airport.request.CreateAirportRequest;
import com.airline.flight.dto.airport.response.AirportResponse;
import com.airline.flight.entity.Airport;
import com.airline.flight.entity.City;
import com.airline.flight.exception.ResourceNotFoundException;
import com.airline.flight.mapper.AirportMapper;
import com.airline.flight.respositories.AirportRepository;
import com.airline.flight.respositories.CityRepository;
import com.airline.flight.services.AirportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class AirportServiceImp implements AirportService {

    @Autowired
    private AirportRepository airportRepository;

    @Autowired
    private CityRepository cityRepository;

    @Override
    public AirportResponse createAirport(CreateAirportRequest createAirportRequest) {
        System.out.println("Inside service createAirport method");
        if(!cityRepository.existsById(createAirportRequest.getCityId())) {
           throw new ResourceNotFoundException("City not found");
        }
        System.out.println("Inside service createAirport method after city call");

        City city = cityRepository.getReferenceById(createAirportRequest.getCityId());

        // DTO to Entity
        Airport airport = AirportMapper.toEntity(createAirportRequest);

        // Set City Reference
        airport.setCity(city);

        // Save Airport
        Airport savedAirport = airportRepository.save(airport);

        AirportResponse airportResponse = AirportMapper.toResponse(savedAirport);

        return airportResponse;
    }

    @Override
    public List<AirportResponse> getAllAirports() {

        List<Airport> airports = airportRepository.findAll();

        List<AirportResponse> airportResponseList = airports.stream().map(AirportMapper::toResponse).toList();

        return airportResponseList;
    }

    @Override
    public AirportResponse getAirport(UUID airportId) {
        Airport airport = airportRepository.findById(airportId)
                .orElseThrow(()-> new ResourceNotFoundException("Airport not found"));

        return AirportMapper.toResponse(airport);
    }

    @Override
    public void deleteAirport(UUID airportId) {
        if(!airportRepository.existsById(airportId)) {
            throw new ResourceNotFoundException("Airport not found");
        }
        // Remove airport
        airportRepository.deleteById(airportId);
    }
}
