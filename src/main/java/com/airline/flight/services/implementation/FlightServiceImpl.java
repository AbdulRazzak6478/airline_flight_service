package com.airline.flight.services.implementation;

import com.airline.flight.dto.common.PageResponse;
import com.airline.flight.dto.flight.request.CreateFlightRequest;
import com.airline.flight.dto.flight.request.FlightSearchRequest;
import com.airline.flight.dto.flight.request.UpdateFlightRequest;
import com.airline.flight.dto.flight.response.FlightResponse;
import com.airline.flight.dto.flight.response.FlightWithAssociatesResponse;
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
import com.airline.flight.specification.FlightSpecification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
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
    public PageResponse<FlightWithAssociatesResponse> getFlights(FlightSearchRequest searchRequest) {

        Sort.Direction direction =
                "DESC".equalsIgnoreCase(searchRequest.getSortDirection())
                        ? Sort.Direction.DESC
                        : Sort.Direction.ASC;

        Pageable pageable = PageRequest.of(
                searchRequest.getPage(),
                searchRequest.getLimit(),
                Sort.by(
                        direction,
                        searchRequest.getSortBy()
                )
        );

        Specification<Flight> spec = null;

        if (searchRequest.getPrice() != null) {

            Specification<Flight> flightPrice = FlightSpecification.hasPrice(
                    searchRequest.getPrice()
            );
            if(spec == null)
            {
                spec = flightPrice;
            }else{
                spec = spec.and(flightPrice);
            }
        }
        if (searchRequest.getFlightDate() != null) {

            Specification<Flight> flightDate = FlightSpecification.hasDepartureDate(
                    searchRequest.getFlightDate()
            );
            if(spec == null)
            {
                spec = flightDate;
            }else{
                spec = spec.and(flightDate);
            }
        }

        if (searchRequest.getDepartureAirportId() != null) {

            Specification<Flight> departureAirport = FlightSpecification.hasDepartureAirport(
                    searchRequest.getDepartureAirportId()
            );
            if(spec == null)
            {
                spec = departureAirport;
            }else{
                spec = spec.and(departureAirport);
            }
        }

        if (searchRequest.getArrivalAirportId() != null) {

            Specification<Flight> arrivalAirport = FlightSpecification.hasArrivalAirport(
                    searchRequest.getArrivalAirportId()
            );
            if(spec == null)
            {
                spec = arrivalAirport;
            }else{
                spec = spec.and(arrivalAirport);
            }
        }

        System.out.println("Departure airport Id : "+ searchRequest.getDepartureAirportId());


        Page<FlightWithAssociatesResponse> flights = flightRepository.findAll(spec, pageable).map(FlightMapper::toFlightWithAssociates);


        return PageResponse.from(flights);


//        flightRepository.findAll

//        System.out.println("Page " + flights.getContent().size() + " " +flights.getTotalElements());
//        System.out.println("Element : "+flights.get().map(FlightMapper::toResponse).toList().getFirst().getBoardingGate());
//        flights.get().map(FlightMapper::toResponse).toList().forEach(System.out::println);

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
