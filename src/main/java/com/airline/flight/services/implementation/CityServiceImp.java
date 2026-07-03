package com.airline.flight.services.implementation;

import com.airline.flight.dto.airport.response.AirportResponse;
import com.airline.flight.dto.city.request.CreateCityRequest;
import com.airline.flight.dto.city.response.CityResponse;
import com.airline.flight.entity.Airport;
import com.airline.flight.entity.City;
import com.airline.flight.exception.DuplicateResourceException;
import com.airline.flight.exception.ResourceNotFoundException;
import com.airline.flight.mapper.AirportMapper;
import com.airline.flight.mapper.CityMapper;
import com.airline.flight.respositories.AirportRepository;
import com.airline.flight.respositories.CityRepository;
import com.airline.flight.services.CityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class CityServiceImp implements CityService {

    @Autowired
    private CityRepository cityRepository;

    @Autowired
    private AirportRepository airportRepository;


    /**
     * @return CityResponse
     */
    @Override
    public CityResponse createCity(CreateCityRequest createCityRequest) {

        // Check City Exist or not
        if(cityRepository.existsByName(createCityRequest.getName().toUpperCase())) {
            throw new DuplicateResourceException("City with name " + createCityRequest.getName() + " already exists");
        }

        // Mapper DTO to Entity
        City city = CityMapper.toEntity(createCityRequest);

        // Save city
        City savedCity = cityRepository.save(city);

        // Entity to DTO and return

        return CityMapper.toResponse(savedCity);
    }

    @Override
    public CityResponse getCity(UUID cityId) {
        // Check City is Exist or not
        City city = cityRepository.findById(cityId)
                .orElseThrow(()-> new ResourceNotFoundException("City not found"));

        // Map Entity to DTO and Return
        return CityMapper.toResponse(city);
    }

    @Override
    public List<CityResponse> getCities() {

        // Get all cities
        List<City> cities = cityRepository.findAll();

        return cities.stream().map(CityMapper::toResponse).toList();
    }

    @Override
    public List<AirportResponse> getCityAirports(UUID cityId) {

        // Check City Exist Or Not
        if(!cityRepository.existsById(cityId)) {
            throw new ResourceNotFoundException("City not found");
        }

        // Get City Airports
        List<Airport> cityAirports = airportRepository.findAllByCityId(cityId);

        // Convert Entity To DTO response
        List<AirportResponse> response = cityAirports.stream().map(AirportMapper::toResponse).toList();

        return response;
    }
}
