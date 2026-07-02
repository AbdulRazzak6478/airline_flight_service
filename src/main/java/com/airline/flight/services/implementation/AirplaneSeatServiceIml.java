package com.airline.flight.services.implementation;

import com.airline.flight.dto.airplane.request.CreateAirplaneSeatRequest;
import com.airline.flight.dto.airplane.response.AirplaneSeatResponse;
import com.airline.flight.entity.Airplane;
import com.airline.flight.entity.AirplaneSeat;
import com.airline.flight.exception.ResourceNotFoundException;
import com.airline.flight.mapper.AirplaneMapper;
import com.airline.flight.respositories.AirplaneRepository;
import com.airline.flight.respositories.AirplaneSeatRepository;
import com.airline.flight.services.AirplaneSeatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class AirplaneSeatServiceIml implements AirplaneSeatService {

    @Autowired
    private AirplaneSeatRepository airplaneSeatRepository;

    @Autowired
    private AirplaneRepository airplaneRepository;


    @Override
    public AirplaneSeatResponse createAirplaneSeat(UUID airplaneId, CreateAirplaneSeatRequest airplaneSeat){

        // Validate Airplane is Exist or not
        if(!airplaneRepository.existsById(airplaneId))
        {
            throw new ResourceNotFoundException("Airplane not found");
        }

        // Get airplane reference
        Airplane airplane = airplaneRepository.getReferenceById(airplaneId);

        AirplaneSeat airplaneSeatEntity = AirplaneMapper.toAirplaneSeatEntity(airplaneSeat);
        airplaneSeatEntity.setAirplane(airplane);

        AirplaneSeat seat = airplaneSeatRepository.save(airplaneSeatEntity);

        return AirplaneMapper.toAirplaneSeatResponse(seat);
    }

    @Override
    public List<AirplaneSeatResponse> getAirplaneSeats(UUID airplaneId){

        // Validate Airplane is Exist or not
        if(!airplaneRepository.existsById(airplaneId))
        {
            throw new ResourceNotFoundException("Airplane not found");
        }

        // Get airplane reference
        Airplane airplane = airplaneRepository.getReferenceById(airplaneId);

        // Get Airplane seats
        List<AirplaneSeat> airplaneSeats = airplaneSeatRepository.getAirplaneSeatsByAirplaneId(airplane.getId());

        // Return airplane seat response
        return airplaneSeats.stream().map(AirplaneMapper::toAirplaneSeatResponse).toList();
    }

    @Override
    public AirplaneSeatResponse getAirplaneById(String id){
        AirplaneSeat seat = airplaneSeatRepository.findById(UUID.fromString(id))
                .orElseThrow(()->new ResourceNotFoundException("Airplane seat not found"));

        return AirplaneMapper.toAirplaneSeatResponse(seat);
    }

    @Override
    public void deleteAirplaneSeat(String id){

        // Validate Airplane is Exist or not
        if(!airplaneSeatRepository.existsById(UUID.fromString(id)))
        {
            throw new ResourceNotFoundException("Airplane seat not found");
        }

        // Get airplane reference
        AirplaneSeat airplaneSeat = airplaneSeatRepository.getReferenceById(UUID.fromString(id));

        // Delete Airplane Seat
        airplaneSeatRepository.delete(airplaneSeat);
    }

}
