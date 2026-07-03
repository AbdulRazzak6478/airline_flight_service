package com.airline.flight.dto.city.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CityResponse {

    private UUID id;
    private String name;
    private String state;
    private String country;
}
