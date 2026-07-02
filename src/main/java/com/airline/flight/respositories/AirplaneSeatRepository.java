package com.airline.flight.respositories;

import com.airline.flight.entity.AirplaneSeat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;


@Repository
public interface AirplaneSeatRepository extends JpaRepository<AirplaneSeat, UUID> {

    public List<AirplaneSeat> getAirplaneSeatsByAirplaneId(UUID airplaneId);
}
