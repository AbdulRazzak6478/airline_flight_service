package com.airline.flight.mapper;


import com.airline.flight.dto.airplane.request.CreateAirplaneRequest;
import com.airline.flight.dto.airplane.request.CreateAirplaneSeatRequest;
import com.airline.flight.dto.airplane.response.AirplaneResponse;
import com.airline.flight.dto.airplane.response.AirplaneSeatResponse;
import com.airline.flight.entity.Airplane;
import com.airline.flight.entity.AirplaneSeat;
import org.springframework.stereotype.Component;

@Component
public class AirplaneMapper {

    public static Airplane toEntity(CreateAirplaneRequest airplaneRequest){

        Airplane airplane = new Airplane();

        airplane.setModelNumber(airplaneRequest.getModelNumber());
        airplane.setManufacturer(airplaneRequest.getManufacturer());
        airplane.setRegistrationNumber(airplaneRequest.getRegistrationNumber());
        airplane.setSeatCapacity(airplaneRequest.getSeatCapacity());

        return airplane;
    }

    public static AirplaneResponse toResponse(Airplane airplane)
    {
        return AirplaneResponse.builder()
                .id(airplane.getId())
                .modelNumber(airplane.getModelNumber())
                .manufacturer(airplane.getManufacturer())
                .registrationNumber(airplane.getRegistrationNumber())
                .seatCapacity(airplane.getSeatCapacity())
                .build();
    }

    public static AirplaneSeat toAirplaneSeatEntity(CreateAirplaneSeatRequest  airplaneSeatRequest)
    {
        AirplaneSeat airplaneSeat = new AirplaneSeat();

        airplaneSeat.setSeatNumber(airplaneSeatRequest.getSeatNumber());
        airplaneSeat.setSeatClass(airplaneSeatRequest.getSeatClass());
        airplaneSeat.setSeatType(airplaneSeatRequest.getSeatType());

        return airplaneSeat;
    }

    public static AirplaneSeatResponse toAirplaneSeatResponse(AirplaneSeat airplaneSeat)
    {
        return AirplaneSeatResponse.builder()
                .id(airplaneSeat.getId())
                .seatNumber(airplaneSeat.getSeatNumber())
                .seatClass(airplaneSeat.getSeatClass())
                .seatType(airplaneSeat.getSeatType())
                .createdAt(airplaneSeat.getCreatedAt())
                .updatedAt(airplaneSeat.getUpdatedAt())
                .build();
    }
}
