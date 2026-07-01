package com.airline.flight.respositories;

import com.airline.flight.entity.Airplane;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface AirplaneRepository extends JpaRepository<Airplane, UUID> {

    public Boolean existsByRegistrationNumber(String registrationNumber);
    public Boolean existsByModelNumber(String modelNumber);
}
