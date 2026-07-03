package com.airline.flight.dto.city.request;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CreateCityRequest {

    @NotBlank(message = "Name is required.")
    private String name;

//    @NotBlank( message = "Address is required.")
//    private String address;

    @NotBlank( message = "State is required.")
    private String state;

    @NotBlank(message = "Country is required.")
    private String country;
}
