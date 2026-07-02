package com.airline.flight.dto.airplane.request;


import com.airline.flight.enums.SeatClass;
import com.airline.flight.enums.SeatType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CreateAirplaneSeatRequest {

    @NotBlank( message = "Seat number is required")
    private String seatNumber;

    @NotNull( message = "Seat class is required")
    private SeatClass seatClass;

    @NotNull( message = "Seat type is required")
    private SeatType seatType;
}
