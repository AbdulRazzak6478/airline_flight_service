package com.airline.flight.dto.airplane.request;


import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CreateAirplaneRequest {

    @NotNull( message = "Model Number is missing.")
    @NotBlank( message = "Model number is required.")
    @Size(max = 100, message = "Model number cannot exceeds 100 characters.")
    private String modelNumber;

    @NotBlank( message = "Manufacturer is required.")
    @Size(max = 100, message = "Manufacturer cannot exceeds 100 characters.")
    private String manufacturer;

    @NotBlank( message = "Registration number is required.")
    @Size(min = 3, max = 50, message = "Registration number must be between 3 and 50 characters.")
    private String registrationNumber;

    @NotNull(message = "Seat capacity is required.")
    @Min(value = 1, message = "Seat capacity must be at least 1.")
    @Max(value = 700, message = "Seat capacity cannot exceed 700.")
    private Integer seatCapacity;

}
