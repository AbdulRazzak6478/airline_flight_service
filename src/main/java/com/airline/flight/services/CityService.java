package com.airline.flight.services;

import com.airline.flight.dto.airport.response.AirportResponse;
import com.airline.flight.dto.city.request.CreateCityRequest;
import com.airline.flight.dto.city.response.CityResponse;

import java.util.List;
import java.util.UUID;

public interface CityService {

    public CityResponse createCity(CreateCityRequest createCityRequest);

    public CityResponse getCity(UUID cityId);

    public List<CityResponse> getCities();

    public List<AirportResponse> getCityAirports(UUID cityId);
}
