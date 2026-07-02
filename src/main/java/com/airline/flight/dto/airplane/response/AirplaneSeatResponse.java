package com.airline.flight.dto.airplane.response;


import com.airline.flight.enums.SeatClass;
import com.airline.flight.enums.SeatType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Locale;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AirplaneSeatResponse {

    private UUID id;

    private String seatNumber;

    private SeatClass seatClass;

    private SeatType  seatType;

//    private UUID airplaneId;

    private LocalDateTime  createdAt;
    private LocalDateTime updatedAt;

}
