package com.airline.flight.services.implementation;

import com.airline.flight.dto.flight.request.CreateFlightRequest;
import com.airline.flight.dto.flight.request.UpdateFlightRequest;
import com.airline.flight.dto.flight.response.FlightResponse;
import com.airline.flight.entity.Airplane;
import com.airline.flight.entity.Airport;
import com.airline.flight.entity.Flight;
import com.airline.flight.exception.ResourceNotFoundException;
import com.airline.flight.mapper.FlightMapper;
import com.airline.flight.respositories.AirplaneRepository;
import com.airline.flight.respositories.AirportRepository;
import com.airline.flight.respositories.FlightRepository;
import com.airline.flight.services.AirplaneService;
import com.airline.flight.services.AirportService;
import com.airline.flight.services.FlightService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;


@Service
public class FlightServiceImpl implements FlightService {

    @Autowired
    private FlightRepository flightRepository;

    @Autowired
    private AirportRepository airportRepository;

    @Autowired
    private AirplaneRepository airplaneRepository;

    @Override
    public FlightResponse createFlight(CreateFlightRequest createFlightRequest) {

        // Date Business Validations
        LocalDateTime departureTime = createFlightRequest.getDepartureTime();
        LocalDateTime arrivalTime = createFlightRequest.getArrivalTime();

        if(arrivalTime.isBefore(departureTime))
        {
            throw new RuntimeException("Arrival Time must be greater then departure time.");
        }

        // Check Airplane Exist or not
        if(!airplaneRepository.existsById(createFlightRequest.getAirplaneId())) {
            throw new ResourceNotFoundException("Airplane not found.");
        }

        // Check Arrival Airport Valid Or Not
        if(!airportRepository.existsById(createFlightRequest.getArrivalAirportId())) {
            throw new ResourceNotFoundException("Arrival Airport not found.");
        }

        // Check Departure Airport Valid Or Not
        if(!airportRepository.existsById(createFlightRequest.getDepartureAirportId())) {
            throw new ResourceNotFoundException("Departure Airport not found.");
        }

        Airplane airplane = airplaneRepository.getReferenceById(createFlightRequest.getAirplaneId());
        Airport arrivalAirport = airportRepository.getReferenceById(createFlightRequest.getArrivalAirportId());
        Airport departureAirport = airportRepository.getReferenceById(createFlightRequest.getDepartureAirportId());

        Flight flight = FlightMapper.toEntity(createFlightRequest);

        // Set Reference
        flight.setAirplane(airplane);
        flight.setArrivalAirport(arrivalAirport);
        flight.setDepartureAirport(departureAirport);

        // Save Flight
        Flight savedFlight = flightRepository.save(flight);

        return FlightMapper.toResponse(savedFlight);
    }

    @Override
    public List<FlightResponse> getAllFlights() {

        List<Flight>  flights = flightRepository.findAll();

        return flights.stream().map(FlightMapper::toResponse).toList();
    }

    @Override
    public FlightResponse getFlight(UUID flightId) {

        Flight flight = flightRepository.findById(flightId)
                .orElseThrow(()-> new ResourceNotFoundException("Flight Not Found"));

        return FlightMapper.toResponse(flight);
    }

    @Override
    public FlightResponse updateFlightDetails(UUID flightId, UpdateFlightRequest updateFlightRequest) {

        // Date Business Validations
        LocalDateTime departureTime = updateFlightRequest.getDepartureTime();
        LocalDateTime arrivalTime = updateFlightRequest.getArrivalTime();

        if(arrivalTime.isBefore(departureTime))
        {
            throw new RuntimeException("Arrival Time must be greater then departure time.");
        }

        // Check Flight Exist Or not
        Flight flight = flightRepository.findById(flightId)
                .orElseThrow(()-> new ResourceNotFoundException("Flight Not Found"));

        // Check Airplane Exist or not
        if(!airplaneRepository.existsById(updateFlightRequest.getAirplaneId())) {
            throw new ResourceNotFoundException("Airplane not found.");
        }
        Airplane airplane = airplaneRepository.getReferenceById(updateFlightRequest.getAirplaneId());

        flight.setArrivalTime(arrivalTime);
        flight.setDepartureTime(departureTime);
        flight.setBoardingGate(updateFlightRequest.getBoardingGate());
        flight.setStatus(updateFlightRequest.getStatus());
        flight.setPrice(updateFlightRequest.getPrice());

        flight.setAirplane(airplane);

        Flight savedFlight = flightRepository.save(flight);

        return FlightMapper.toResponse(savedFlight);
    }
}
