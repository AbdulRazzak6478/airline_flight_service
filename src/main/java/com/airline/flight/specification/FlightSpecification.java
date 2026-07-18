package com.airline.flight.specification;

import com.airline.flight.entity.Flight;
import org.springframework.data.jpa.domain.Specification;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

public class FlightSpecification {

    public static Specification<Flight> hasPrice(BigDecimal price) {

        return (root,query,cb)->
                cb.greaterThanOrEqualTo(
                        root.get("price"),
                        price
                );
    }

    public static Specification<Flight> hasArrivalDate(LocalDate arrivalDate) {

        return (root,query,cb)->
                cb.between(
                        root.get("arrivalTime"),
                        arrivalDate.atStartOfDay(),
                        arrivalDate.atTime(23,59,59)
                );
    }

    public static Specification<Flight> hasDepartureDate(LocalDate departureTime) {

        return (root,query,cb)->
                cb.between(
                root.get("departureTime"),
                        departureTime.atStartOfDay(),
                        departureTime.atTime(23,59,59)
        );
    }

    public static Specification<Flight> hasManufacturer(String manufacturer) {
        return (root,query,cb) ->
                cb.equal(
                        root.get("airplane")
                                .get("manufacturer"),
                        manufacturer
                );
    }

    public static Specification<Flight> hasAirplane(UUID airplaneId) {
        return (root,query,cb) ->
                cb.equal(
                        root.get("airplane")
                                .get("id"),
                        airplaneId
                );
    }


    public static Specification<Flight> hasArrivalAirport(UUID arrivalAirportId) {
        return (root,query,cb) ->
                cb.equal(
                        root.get("arrivalAirport")
                                .get("id"),
                        arrivalAirportId
                );
    }

    public static Specification<Flight> hasDepartureAirport(UUID departureAirportId) {
        return (root,query,cb) ->
                cb.equal(
                        root.get("departureAirport")
                                .get("id"),
                        departureAirportId
                );
    }
}
