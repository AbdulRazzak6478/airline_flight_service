package com.airline.flight.dto.flight.request;

import com.airline.flight.enums.FlightStatus;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UpdateFlightRequest {

    @NotNull( message = "Departure Time is required." )
    @Future( message = "Departure time must be in the future time.")
    private LocalDateTime departureTime;

    @NotNull( message = "Arrival Time is required." )
    @Future( message = "Arrival time must be in the future.")
    private LocalDateTime arrivalTime;

    @NotNull( message = "Price is required." )
    @DecimalMin(
            value = "0.01",
            inclusive = true,
            message = "Price must be greater then zero."
    )
    @Digits(
            integer = 8,
            fraction = 2,
            message = "Price can have up to 8 digits before decimal and 2 digits after decimal."
    )
    private BigDecimal price;

    @NotNull( message = "Status is required.")
    private FlightStatus status;

    @NotBlank(message = "Boarding gate is required.")
    @Size(
            min = 2,
            max = 10,
            message = "Boarding gate must be between 2 and 10 characters."
    )
    @Pattern(
            regexp = "^[A-Z0-9-]+$",
            message = "Boarding gate can contain only uppercase letters, numbers and hyphen."
    )
    private String boardingGate;

    @NotNull( message = "Airplane id is required.")
    private UUID airplaneId;
}
