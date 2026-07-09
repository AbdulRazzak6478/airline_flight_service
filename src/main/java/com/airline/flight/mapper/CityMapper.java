package com.airline.flight.mapper;

import com.airline.flight.dto.city.request.CreateCityRequest;
import com.airline.flight.dto.city.response.CityResponse;
import com.airline.flight.entity.City;
import org.springframework.stereotype.Component;

@Component
public class CityMapper {

    public static City toEntity(CreateCityRequest createCityRequest) {
        City city = new City();
        city.setName(createCityRequest.getName().toUpperCase());
        city.setCode(createCityRequest.getCode());
        city.setCountry(createCityRequest.getCountry());
        city.setState(createCityRequest.getState());
        return city;
    }

    public static CityResponse  toResponse(City city) {
        CityResponse cityResponse = new CityResponse();

        cityResponse.setId(city.getId());
        cityResponse.setCode(city.getCode());
        cityResponse.setName(city.getName());
        cityResponse.setCountry(city.getCountry());
        cityResponse.setState(city.getState());
        return cityResponse;
    }
}
