package com.airline.flight.dto.flight.request;


import com.airline.flight.enums.SeatClass;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class FlightSearchRequest {

    private Integer page = 0;

    private Integer limit = 10;

    private BigDecimal price;

    private String sortBy = "departureTime";

    private String sortDirection = "ASEC";

    private UUID departureAirportId;

    private UUID arrivalAirportId;

    private LocalDate flightDate;

//    private LocalDate returnDate;

//    private Integer travellers;
//
//    private SeatClass seatClass;
}
