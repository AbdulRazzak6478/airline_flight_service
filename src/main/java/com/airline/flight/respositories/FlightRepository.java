package com.airline.flight.respositories;

import com.airline.flight.entity.Flight;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;


@Repository
public interface FlightRepository extends JpaRepository<Flight, UUID> {
}
